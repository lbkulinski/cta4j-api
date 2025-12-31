package app.cta4j.busroute.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import org.jspecify.annotations.NullMarked;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@Value
@Builder
@NullMarked
@DynamoDbImmutable(builder = Route.RouteBuilder.class)
public class Route {
    @Getter(onMethod_ = @DynamoDbPartitionKey)
    String id;

    String name;
}
