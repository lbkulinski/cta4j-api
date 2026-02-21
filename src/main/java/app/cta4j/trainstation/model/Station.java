package app.cta4j.trainstation.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import org.jspecify.annotations.NullMarked;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

@Value
@Builder
@NullMarked
@DynamoDbImmutable(builder = Station.StationBuilder.class)
public class Station {
    @Getter(onMethod_ = @DynamoDbPartitionKey)
    String id;

    @Getter
    String name;
}
