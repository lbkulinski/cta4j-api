package com.cta4j.api.bus.route.model;

import lombok.Builder;
import org.jspecify.annotations.NullMarked;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.List;
import java.util.Objects;

@Builder
@DynamoDbImmutable(builder = RouteDirections.RouteDirectionsBuilder.class)
@NullMarked
public record RouteDirections(
    @DynamoDbPartitionKey String routeId,
    List<String> directions
) {
    public RouteDirections {
        Objects.requireNonNull(routeId);
        Objects.requireNonNull(directions);

        directions = List.copyOf(directions);
    }
}
