package app.cta4j.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.List;

@Value
@Builder
@DynamoDbImmutable(builder = RouteDirections.RouteDirectionsBuilder.class)
public class RouteDirections {
    @Getter(onMethod_ = @DynamoDbPartitionKey)
    String routeId;

    @Getter
    List<String> directions;
}
