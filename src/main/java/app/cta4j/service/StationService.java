package app.cta4j.service;

import app.cta4j.dto.StationDto;
import app.cta4j.model.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;
import java.util.Objects;

@Service
public class StationService {
    private final DynamoDbTable<Station> stations;

    @Autowired
    public StationService(Environment env, DynamoDbEnhancedClient dynamoDbClient) {
        Objects.requireNonNull(env);

        Objects.requireNonNull(dynamoDbClient);

        String stationsTableName = env.getRequiredProperty("app.aws.dynamodb.tables.stations");

        TableSchema<Station> stationsSchema = TableSchema.fromImmutableClass(Station.class);

        this.stations = dynamoDbClient.table(stationsTableName, stationsSchema);
    }

    @Cacheable("stations")
    public List<StationDto> getStations() {
        return this.stations.scan()
                            .items()
                            .stream()
                            .map(StationDto::from)
                            .toList();
    }
}
