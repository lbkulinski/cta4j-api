package com.cta4j.api.bus.route.repository;

import com.cta4j.api.aws.config.DynamoDbProperties;
import com.cta4j.api.bus.route.exception.RouteNotFoundException;
import com.cta4j.api.bus.route.model.RouteDirections;
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
public class RouteDirectionsRepository {
    private final DynamoDbTable<@Nullable RouteDirections> routeDirections;

    @Autowired
    public RouteDirectionsRepository(
        DynamoDbEnhancedClient dynamoDbClient,
        DynamoDbProperties tableProperties
    ) {
        TableSchema<RouteDirections> schema = TableSchema.fromImmutableClass(RouteDirections.class);

        this.routeDirections = dynamoDbClient.table(tableProperties.routeDirections(), schema);
    }

    @Cacheable("directionsByRouteId")
    public List<String> findAllByRouteId(String routeId) {
        Objects.requireNonNull(routeId);

        Key key = Key.builder()
                     .partitionValue(routeId)
                     .build();

        RouteDirections item = this.routeDirections.getItem(key);

        if (item == null) {
            throw new RouteNotFoundException(routeId);
        }

        return List.copyOf(item.directions());
    }
}
