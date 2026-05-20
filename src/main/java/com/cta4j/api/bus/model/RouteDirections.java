package com.cta4j.api.bus.model;

import lombok.Builder;
import org.jspecify.annotations.NullMarked;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.List;

@Builder
@DynamoDbImmutable(builder = RouteDirections.RouteDirectionsBuilder.class)
@NullMarked
public record RouteDirections(
    @DynamoDbPartitionKey String route,
    List<String> directions
) {}
