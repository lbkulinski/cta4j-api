package app.cta4j.train.service;

import app.cta4j.train.dto.Station;
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
    private final DynamoDbTable<app.cta4j.train.model.Station> stations;

    @Autowired
    public StationService(Environment env, DynamoDbEnhancedClient dynamoDbClient) {
        Objects.requireNonNull(env);

        Objects.requireNonNull(dynamoDbClient);

        String stationsTableName = env.getRequiredProperty("app.aws.dynamodb.tables.stations");

        var stationsSchema = TableSchema.fromImmutableClass(app.cta4j.train.model.Station.class);

        this.stations = dynamoDbClient.table(stationsTableName, stationsSchema);
    }

    @Cacheable("stations")
    public List<Station> getStations() {
        return this.stations.scan()
                            .items()
                            .stream()
                            .map(Station::from)
                            .toList();
    }
}
