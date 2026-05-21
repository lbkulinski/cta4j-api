package com.cta4j.api.bus.model;

import lombok.Builder;
import org.jspecify.annotations.NullMarked;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable;

import java.util.Objects;

@Builder
@DynamoDbImmutable(builder = RouteStop.RouteStopBuilder.class)
@NullMarked
public record RouteStop(
    String id,
    String name
) {
    public RouteStop {
        Objects.requireNonNull(id);
        Objects.requireNonNull(name);
    }
}
