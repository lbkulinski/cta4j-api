package app.cta4j.trainstation.service;

import app.cta4j.trainstation.dto.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.mapper.ImmutableTableSchema;

import java.util.List;
import java.util.Objects;

@Service
public class StationService {
    private final DynamoDbTable<app.cta4j.trainstation.model.Station> stations;

    @Autowired
    public StationService(Environment env, DynamoDbEnhancedClient dynamoDbClient) {
        Objects.requireNonNull(env);

        Objects.requireNonNull(dynamoDbClient);

        String stationsTableName = env.getRequiredProperty("app.aws.dynamodb.tables.stations");

        ImmutableTableSchema<app.cta4j.trainstation.model.Station> stationsSchema = TableSchema.fromImmutableClass(app.cta4j.trainstation.model.Station.class);

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
