package app.cta4j.bus.model;

import lombok.Builder;
import lombok.Value;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable;

@Value
@Builder
@DynamoDbImmutable(builder = BusStop.BusStopBuilder.class)
public class BusStop {
    String id;

    String name;
}
