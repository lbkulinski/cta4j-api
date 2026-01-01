package app.cta4j.busroute.repository;

import app.cta4j.busroute.model.RouteStops;
import app.cta4j.busroute.model.Stop;
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
public class StopRepository {
    private final DynamoDbTable<RouteStops> stops;

    @Autowired
    public StopRepository(
        DynamoDbEnhancedClient dynamoDbClient,
        @Value("${app.aws.dynamodb.tables.route-stops}") String tableName
    ) {
        TableSchema<RouteStops> schema = TableSchema.fromImmutableClass(RouteStops.class);

        this.stops = dynamoDbClient.table(tableName, schema);
    }

    @Cacheable(value = "stops", key = "#routeId + '_' + #direction")
    public Optional<List<Stop>> findAllByRouteIdAndDirection(String routeId, String direction) {
        if (routeId == null) {
            throw new IllegalArgumentException("routeId must not be null");
        }

        if (direction == null) {
            throw new IllegalArgumentException("direction must not be null");
        }

        Key key = Key.builder()
                     .partitionValue(routeId)
                     .sortValue(direction)
                     .build();

        RouteStops item = this.stops.getItem(key);

        if (item == null) {
            return Optional.empty();
        }

        List<Stop> stops = item.getStops();
        List<Stop> copy = List.copyOf(stops);

        return Optional.of(copy);
    }
}
