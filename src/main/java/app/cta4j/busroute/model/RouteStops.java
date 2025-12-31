package app.cta4j.busroute.model;

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
@NullMarked
@DynamoDbImmutable(builder = RouteStops.RouteStopsBuilder.class)
public class RouteStops {
    @Getter(onMethod_ = @DynamoDbPartitionKey)
    String routeId;

    @Getter(onMethod_ = @DynamoDbSortKey)
    String direction;

    List<Stop> stops;
}
