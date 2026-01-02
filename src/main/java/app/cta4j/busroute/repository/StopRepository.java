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
import java.util.Objects;

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

    public boolean existsByRouteIdAndStopId(String routeId, String stopId) {
        if (routeId == null) {
            throw new IllegalArgumentException("routeId must not be null");
        }

        if (stopId == null) {
            throw new IllegalArgumentException("stopId must not be null");
        }

        Key key = Key.builder()
                     .partitionValue(routeId)
                     .build();

        RouteStops item = this.stops.getItem(key);

        if (item == null) {
            return false;
        }

        List<Stop> stops = item.getStops();

        return stops.stream()
                    .map(Stop::getId)
                    .anyMatch(id -> Objects.equals(id, stopId));
    }

    @Cacheable(value = "stops", key = "#routeId + '_' + #direction")
    public List<Stop> findAllByRouteIdAndDirection(String routeId, String direction) {
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
            return List.of();
        }

        List<Stop> stops = item.getStops();

        return List.copyOf(stops);
    }
}
