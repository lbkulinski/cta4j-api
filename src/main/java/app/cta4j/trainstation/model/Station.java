package app.cta4j.trainstation.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

@Value
@Builder
@DynamoDbImmutable(builder = Station.StationBuilder.class)
public class Station {
    @Getter(onMethod_ = @DynamoDbPartitionKey)
    String id;

    @Getter
    String name;
}
