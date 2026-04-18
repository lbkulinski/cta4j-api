package com.cta4j.api.bus.repository;

import com.cta4j.api.aws.config.DynamoDbTableProperties;
import com.cta4j.api.bus.exception.RouteNotFoundException;
import com.cta4j.api.bus.model.RouteStops;
import com.cta4j.api.bus.model.Stop;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

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

    @Cacheable("stopsByRouteId")
    public List<Stop> findAllByRouteId(String routeId) {
        Objects.requireNonNull(routeId, "routeId must not be null");

        Key key = Key.builder().partitionValue(routeId)
                     .build();

        QueryConditional queryConditional = QueryConditional.keyEqualTo(key);

        return this.stops.query(queryConditional)
                         .items()
                         .stream()
                         .filter(Objects::nonNull)
                         .flatMap(item -> item.getStops()
                                              .stream())
                         .toList();
    }

    @Cacheable("stopsByRouteIdAndDirection")
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

        return List.copyOf(stops);
    }
}
