package com.cta4j.api.bus.model;

import lombok.Builder;
import org.jspecify.annotations.NullMarked;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import java.util.List;

@Builder
@DynamoDbImmutable(builder = RouteStops.RouteStopsBuilder.class)
@NullMarked
public record RouteStops(
    @DynamoDbPartitionKey String route,
    @DynamoDbSortKey String direction,
    List<RouteStop> stops
) {}
