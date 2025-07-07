package app.cta4j.model;

import lombok.Builder;
import lombok.Value;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable;

@Value
@Builder
@DynamoDbImmutable(builder = Stop.StopBuilder.class)
public class Stop {
    String id;

    String name;
}
