package app.cta4j.train.service;

import app.cta4j.train.client.TrainApiClient;
import app.cta4j.train.dto.*;
import app.cta4j.train.model.TrainStation;
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
    private final DynamoDbTable<TrainStation> stations;

    private final TrainApiClient trainApiClient;

    @Autowired
    public TrainService(Environment env, DynamoDbEnhancedClient dynamoDbClient, TrainApiClient trainApiClient) {
        Objects.requireNonNull(env);

        Objects.requireNonNull(dynamoDbClient);

        Objects.requireNonNull(trainApiClient);

        String stationsTableName = env.getRequiredProperty("app.aws.dynamodb.tables.stations");

        TableSchema<TrainStation> stationsSchema = TableSchema.fromImmutableClass(TrainStation.class);

        this.stations = dynamoDbClient.table(stationsTableName, stationsSchema);

        this.trainApiClient = trainApiClient;
    }

    @Cacheable("trainStations")
    public List<TrainStationDto> getStations() {
        return this.stations.scan()
                            .items()
                            .stream()
                            .map(TrainStationDto::from)
                            .toList();
    }

    public List<TrainArrivalDto> getArrivals(String stationId) {
        Objects.requireNonNull(stationId);

        TrainArrivalResponseDto response = this.trainApiClient.getArrivals(stationId);

        if (response == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        TrainArrivalBodyDto body = response.body();

        if (body == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        List<TrainArrivalDto> arrivals = body.arrivals();

        if ((arrivals == null) || arrivals.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return List.copyOf(arrivals);
    }

    public FollowTrainDto followTrain(int run) {
        if (run <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        FollowTrainResponseDto response = this.trainApiClient.followTrain(run);

        if (response == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        FollowTrainBodyDto body = response.body();

        if (body == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        FollowTrainPositionDto position = body.position();

        if (position == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        List<FollowTrainArrivalDto> arrivals = body.arrivals();

        if ((arrivals == null) || arrivals.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return new FollowTrainDto(position, arrivals);
    }
}
