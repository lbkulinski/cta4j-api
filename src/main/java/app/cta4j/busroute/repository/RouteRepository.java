package app.cta4j.busroute.repository;

import app.cta4j.busroute.model.Route;
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
public class RouteRepository {
    private final DynamoDbTable<Route> routes;

    @Autowired
    public RouteRepository(
        DynamoDbEnhancedClient dynamoDbClient,
        @Value("${app.aws.dynamodb.tables.routes}") String tableName
    ) {
        TableSchema<Route> schema = TableSchema.fromImmutableClass(Route.class);

        this.routes = dynamoDbClient.table(tableName, schema);
    }

    public boolean existsById(String id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }

        Key key = Key.builder()
                     .partitionValue(id)
                     .build();

        Route item = this.routes.getItem(key);

        return item != null;
    }

    @Cacheable(value = "routes", key = "'all'")
    public List<Route> findAll() {
        return routes.scan()
                     .items()
                     .stream()
                     .toList();
    }
}
