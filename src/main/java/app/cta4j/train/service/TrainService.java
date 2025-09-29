package app.cta4j.train.service;

import app.cta4j.train.client.TrainApiClient;
import app.cta4j.train.dto.*;
import app.cta4j.train.external.arrivals.CtaArrivalsCtatt;
import app.cta4j.train.external.arrivals.CtaArrivalsEta;
import app.cta4j.train.external.arrivals.CtaArrivalsResponse;
import app.cta4j.train.external.follow.CtaFollowCtatt;
import app.cta4j.train.external.follow.CtaFollowEta;
import app.cta4j.train.external.follow.CtaFollowPosition;
import app.cta4j.train.external.follow.CtaFollowResponse;
import app.cta4j.train.mapper.FollowTrainMapper;
import app.cta4j.train.mapper.TrainArrivalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;
import java.util.Objects;

@Service
public class TrainService {
    private final DynamoDbTable<app.cta4j.train.model.TrainStation> stations;

    private final TrainApiClient trainApiClient;

    private final TrainArrivalMapper trainArrivalMapper;

    private final FollowTrainMapper followTrainMapper;

    @Autowired
    public TrainService(Environment env, DynamoDbEnhancedClient dynamoDbClient, TrainApiClient trainApiClient,
        TrainArrivalMapper trainArrivalMapper, FollowTrainMapper followTrainMapper) {
        Objects.requireNonNull(env);

        Objects.requireNonNull(dynamoDbClient);

        Objects.requireNonNull(trainApiClient);

        Objects.requireNonNull(trainArrivalMapper);

        Objects.requireNonNull(followTrainMapper);

        String stationsTableName = env.getRequiredProperty("app.aws.dynamodb.tables.stations");

        TableSchema<app.cta4j.train.model.TrainStation> stationsSchema = TableSchema.fromImmutableClass(app.cta4j.train.model.TrainStation.class);

        this.stations = dynamoDbClient.table(stationsTableName, stationsSchema);

        this.trainApiClient = trainApiClient;

        this.trainArrivalMapper = trainArrivalMapper;

        this.followTrainMapper = followTrainMapper;
    }

    @Cacheable("trainStations")
    public List<TrainStation> getStations() {
        return this.stations.scan()
                            .items()
                            .stream()
                            .map(TrainStation::from)
                            .toList();
    }

    public List<TrainArrival> getArrivals(String stationId) {
        Objects.requireNonNull(stationId);

        CtaArrivalsResponse response = this.trainApiClient.getArrivals(stationId);

        if (response == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        CtaArrivalsCtatt ctatt = response.ctatt();

        if (ctatt == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        List<CtaArrivalsEta> eta = ctatt.eta();

        if ((eta == null) || eta.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        List<TrainArrival> arrivals = this.trainArrivalMapper.toDomainList(eta);

        return List.copyOf(arrivals);
    }

    public FollowTrain followTrain(int run) {
        if (run <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        CtaFollowResponse response = this.trainApiClient.followTrain(run);

        if (response == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        CtaFollowCtatt ctatt = response.ctatt();

        if (ctatt == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        CtaFollowPosition ctaPosition = ctatt.position();

        if (ctaPosition == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        List<CtaFollowEta> eta = ctatt.eta();

        if ((eta == null) || eta.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        FollowTrainPosition position = this.followTrainMapper.toDomainPosition(ctaPosition);

        List<FollowTrainArrival> arrivals = this.followTrainMapper.toDomainArrivalList(eta);

        return new FollowTrain(position, arrivals);
    }
}
