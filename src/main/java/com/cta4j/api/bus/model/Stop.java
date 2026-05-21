package com.cta4j.api.bus.model;

import lombok.Builder;
import org.jspecify.annotations.NullMarked;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.math.BigDecimal;
import java.util.Objects;

@Builder
@DynamoDbImmutable(builder = Stop.StopBuilder.class)
@NullMarked
public record Stop(
    @DynamoDbPartitionKey String id,
    String name,
    BigDecimal latitude,
    BigDecimal longitude
) {
    public Stop {
        Objects.requireNonNull(id);
        Objects.requireNonNull(name);
        Objects.requireNonNull(latitude);
        Objects.requireNonNull(longitude);
    }
}
