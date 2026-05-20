package com.cta4j.api.bus.repository;

import com.cta4j.api.aws.config.DynamoDbTableProperties;
import com.cta4j.api.bus.exception.RouteNotFoundException;
import com.cta4j.api.bus.model.RouteStops;
import com.cta4j.api.bus.model.RouteStop;
import org.jspecify.annotations.NullMarked;
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
public class RouteStopRepository {
    private final DynamoDbTable<RouteStops> routeStops;

    @Autowired
    public RouteStopRepository(
        DynamoDbEnhancedClient dynamoDbClient,
        DynamoDbTableProperties tableProperties
    ) {
        TableSchema<RouteStops> schema = TableSchema.fromImmutableClass(RouteStops.class);

        this.routeStops = dynamoDbClient.table(tableProperties.routeStops(), schema);
    }

    @Cacheable("routeStops")
    public List<RouteStop> findAllByRouteAndDirection(String route, String direction) {
        Objects.requireNonNull(route);
        Objects.requireNonNull(direction);

        Key key = Key.builder()
                     .partitionValue(route)
                     .sortValue(direction)
                     .build();

        RouteStops item = this.routeStops.getItem(key);

        if (item == null) {
            //TODO: fix RouteNotFoundException to include direction as well in a constructor overload
            String message = "Route with id '%s' and direction '%s' not found".formatted(route, direction);

            throw new RouteNotFoundException(message);
        }

        List<RouteStop> stops = item.stops();

        return List.copyOf(stops);
    }
}
