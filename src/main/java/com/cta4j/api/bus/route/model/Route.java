package com.cta4j.api.bus.route.model;

import lombok.Builder;
import org.jspecify.annotations.NullMarked;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.Objects;

@Builder
@DynamoDbImmutable(builder = Route.RouteBuilder.class)
@NullMarked
public record Route(
    @DynamoDbPartitionKey String id,
    String designator,
    String hexColor,
    String name
) {
    public Route {
        Objects.requireNonNull(id);
        Objects.requireNonNull(designator);
        Objects.requireNonNull(hexColor);
        Objects.requireNonNull(name);
    }
}
