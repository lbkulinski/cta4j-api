package app.cta4j.busroute.model;

import lombok.Builder;
import lombok.Value;
import org.jspecify.annotations.NullMarked;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable;

@Value
@Builder
@NullMarked
@DynamoDbImmutable(builder = Stop.StopBuilder.class)
public class Stop {
    String id;

    String name;
}
