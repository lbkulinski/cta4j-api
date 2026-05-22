package com.cta4j.api.bus.repository;

import com.cta4j.api.aws.config.DynamoDbTableProperties;
import com.cta4j.api.bus.exception.RouteNotFoundException;
import com.cta4j.api.bus.model.RouteStops;
import com.cta4j.api.bus.model.RouteStop;
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
public class RouteStopRepository {
    private final DynamoDbTable<@Nullable RouteStops> routeStops;

    @Autowired
    public RouteStopRepository(
        DynamoDbEnhancedClient dynamoDbClient,
        DynamoDbTableProperties tableProperties
    ) {
        TableSchema<RouteStops> schema = TableSchema.fromImmutableClass(RouteStops.class);

        this.routeStops = dynamoDbClient.table(tableProperties.routeStops(), schema);
    }

    @Cacheable("stopsByRouteIdAndDirection")
    public List<RouteStop> getAllByRouteIdAndDirection(String routeId, String direction) {
        Objects.requireNonNull(routeId);
        Objects.requireNonNull(direction);

        Key key = Key.builder()
                     .partitionValue(routeId)
                     .sortValue(direction)
                     .build();

        RouteStops item = this.routeStops.getItem(key);

        if (item == null) {
            throw new RouteNotFoundException(routeId, direction);
        }

        List<RouteStop> stops = item.stops();

        return List.copyOf(stops);
    }
}
