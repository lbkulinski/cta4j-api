package app.cta4j.busroute.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import org.jspecify.annotations.NullMarked;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.List;

@Value
@Builder
@NullMarked
@DynamoDbImmutable(builder = RouteDirections.RouteDirectionsBuilder.class)
public class RouteDirections {
    @Getter(onMethod_ = @DynamoDbPartitionKey)
    String routeId;

    List<String> directions;
}
