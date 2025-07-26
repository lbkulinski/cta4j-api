package app.cta4j.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@Value
@Builder
@DynamoDbImmutable(builder = BusRoute.BusRouteBuilder.class)
public class BusRoute {
    @Getter(onMethod_ = @DynamoDbPartitionKey)
    String id;

    String name;
}
