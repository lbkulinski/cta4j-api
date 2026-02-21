package app.cta4j.trainstation.repository;

import app.cta4j.trainstation.model.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;

@Repository
public class StationRepository {
    private final DynamoDbTable<Station> stations;

    @Autowired
    public StationRepository(
        DynamoDbEnhancedClient dynamoDbClient,
        @Value("${app.aws.dynamodb.tables.stations}") String tableName
    ) {
        TableSchema<Station> tableSchema = TableSchema.fromImmutableClass(Station.class);

        this.stations = dynamoDbClient.table(tableName, tableSchema);
    }

    public boolean existsById(String id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }

        Key key = Key.builder()
                     .partitionValue(id)
                     .build();

        Station item = this.stations.getItem(key);

        return item != null;
    }

    @Cacheable("stations")
    public List<Station> findAll() {
        return this.stations.scan()
                            .items()
                            .stream()
                            .toList();
    }
}
