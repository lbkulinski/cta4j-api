package app.cta4j.bus.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import org.jspecify.annotations.NullMarked;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import java.util.List;

@Value
@Builder
@DynamoDbImmutable(builder = RouteStops.RouteStopsBuilder.class)
@NullMarked
public class RouteStops {
    @Getter(onMethod_ = @DynamoDbPartitionKey)
    String routeId;

    @Getter(onMethod_ = @DynamoDbSortKey)
    String direction;

    @Getter
    List<Stop> stops;
}
