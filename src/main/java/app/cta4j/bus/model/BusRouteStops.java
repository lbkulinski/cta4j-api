package app.cta4j.bus.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.util.List;

@Value
@Builder
@DynamoDbImmutable(builder = BusRouteStops.BusRouteStopsBuilder.class)
public class BusRouteStops {
    @Getter(onMethod_ = @DynamoDbPartitionKey)
    String routeId;

    @Getter(onMethod_ = @DynamoDbSortKey)
    String direction;

    @Getter
    List<BusStop> stops;
}
