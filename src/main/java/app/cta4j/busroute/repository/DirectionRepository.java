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
import java.util.Optional;

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
    public Optional<List<String>> findAllByRouteId(String routeId) {
        if (routeId == null) {
            throw new IllegalArgumentException("routeId must not be null");
        }

        Key key = Key.builder()
                     .partitionValue(routeId)
                     .build();

        RouteDirections item = this.routeDirections.getItem(key);

        if (item == null) {
            return Optional.empty();
        }

        List<String> directions = item.getDirections();

        if (directions.isEmpty()) {
            return Optional.of(List.of());
        }

        return Optional.of(List.copyOf(directions));
    }
}
