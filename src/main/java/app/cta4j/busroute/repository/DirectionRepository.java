package app.cta4j.busroute.repository;

import app.cta4j.busroute.model.RouteDirections;
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
public class DirectionRepository {
    private final DynamoDbTable<RouteDirections> routeDirections;

    @Autowired
    public DirectionRepository(
        DynamoDbEnhancedClient dynamoDbClient,
        @Value("${app.aws.dynamodb.tables.route-directions}") String tableName
    ) {
        TableSchema<RouteDirections> schema = TableSchema.fromImmutableClass(RouteDirections.class);

        this.routeDirections = dynamoDbClient.table(tableName, schema);
    }

    @Cacheable("directions")
    public List<String> findAllByRouteId(String routeId) {
        if (routeId == null) {
            throw new IllegalArgumentException("routeId must not be null");
        }

        Key key = Key.builder()
                     .partitionValue(routeId)
                     .build();

        RouteDirections item = this.routeDirections.getItem(key);

        if (item == null) {
            return List.of();
        }

        List<String> directions = item.getDirections();

        if ((directions == null) || directions.isEmpty()) {
            return List.of();
        }

        return List.copyOf(directions);
    }
}
