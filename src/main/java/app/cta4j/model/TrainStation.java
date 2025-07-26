package app.cta4j.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

@Value
@Builder
@DynamoDbImmutable(builder = TrainStation.TrainStationBuilder.class)
public class TrainStation {
    @Getter(onMethod_ = @DynamoDbPartitionKey)
    String id;

    @Getter
    String name;
}
