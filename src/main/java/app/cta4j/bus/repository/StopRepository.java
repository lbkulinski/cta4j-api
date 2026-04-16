package app.cta4j.bus.repository;

import app.cta4j.aws.config.DynamoDbTableProperties;
import app.cta4j.bus.exception.RouteNotFoundException;
import app.cta4j.bus.exception.StopNotFoundException;
import app.cta4j.bus.model.RouteStops;
import app.cta4j.bus.model.Stop;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;
import java.util.Objects;

@Repository
@NullMarked
public class StopRepository {
    private final DynamoDbTable<@Nullable RouteStops> stops;

    @Autowired
    public StopRepository(
        DynamoDbEnhancedClient dynamoDbClient,
        DynamoDbTableProperties tableProperties
    ) {
        TableSchema<RouteStops> schema = TableSchema.fromImmutableClass(RouteStops.class);

        this.stops = dynamoDbClient.table(tableProperties.routeStops(), schema);
    }

    @Cacheable("stops")
    public List<Stop> findAllByRouteIdAndDirection(String routeId, String direction) {
        Objects.requireNonNull(routeId, "routeId must not be null");
        Objects.requireNonNull(direction, "direction must not be null");

        Key key = Key.builder()
                     .partitionValue(routeId)
                     .sortValue(direction)
                     .build();

        RouteStops item = this.stops.getItem(key);

        if (item == null) {
            String message = "Route with id '%s' and direction '%s' not found".formatted(routeId, direction);

            throw new RouteNotFoundException(message);
        }

        List<Stop> stops = item.getStops();

        if (stops.isEmpty()) {
            String message = "Stops for route with id '%s' and direction '%s' not found".formatted(routeId, direction);

            throw new StopNotFoundException(message);
        }

        return List.copyOf(stops);
    }
}
