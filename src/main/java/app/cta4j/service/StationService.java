package app.cta4j.service;

import app.cta4j.client.StationArrivalClient;
import app.cta4j.dto.StationArrivalBodyDto;
import app.cta4j.dto.StationArrivalDto;
import app.cta4j.dto.StationArrivalResponseDto;
import app.cta4j.dto.StationDto;
import app.cta4j.model.Station;
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
public class StationService {
    private final DynamoDbTable<Station> stations;

    private final StationArrivalClient stationArrivalClient;

    @Autowired
    public StationService(Environment env, DynamoDbEnhancedClient dynamoDbClient,
        StationArrivalClient stationArrivalClient) {
        Objects.requireNonNull(env);

        Objects.requireNonNull(dynamoDbClient);

        String stationsTableName = env.getRequiredProperty("app.aws.dynamodb.tables.stations");

        TableSchema<Station> stationsSchema = TableSchema.fromImmutableClass(Station.class);

        this.stations = dynamoDbClient.table(stationsTableName, stationsSchema);

        this.stationArrivalClient = Objects.requireNonNull(stationArrivalClient);
    }

    @Cacheable("stations")
    public List<StationDto> getStations() {
        return this.stations.scan()
                            .items()
                            .stream()
                            .map(StationDto::from)
                            .toList();
    }

    public List<StationArrivalDto> getArrivals(String stationId) {
        Objects.requireNonNull(stationId);

        StationArrivalResponseDto response = this.stationArrivalClient.getArrivals(stationId);

        if (response == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        StationArrivalBodyDto body = response.body();

        if (body == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        List<StationArrivalDto> arrivals = body.arrivals();

        if ((arrivals == null) || arrivals.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return List.copyOf(arrivals);
    }
}
