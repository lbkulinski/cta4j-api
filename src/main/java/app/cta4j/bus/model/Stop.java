package app.cta4j.bus.model;

import lombok.Builder;
import lombok.Value;
import org.jspecify.annotations.NullMarked;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable;

@Value
@Builder
@DynamoDbImmutable(builder = Stop.StopBuilder.class)
@NullMarked
public class Stop {
    String id;

    String name;
}
