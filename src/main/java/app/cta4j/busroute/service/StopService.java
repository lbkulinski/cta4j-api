package app.cta4j.busroute.service;

import app.cta4j.busroute.dto.Stop;
import app.cta4j.busroute.model.RouteStops;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;
import java.util.Objects;

@Service
public class StopService {
    private final DynamoDbTable<RouteStops> stops;

    @Autowired
    public StopService(Environment env, DynamoDbEnhancedClient dynamoDbClient) {
        String tableName = env.getRequiredProperty("app.aws.dynamodb.tables.route-stops");

        TableSchema<RouteStops> schema = TableSchema.fromImmutableClass(RouteStops.class);

        this.stops = dynamoDbClient.table(tableName, schema);
    }

    @Cacheable("stops")
    public List<Stop> getStops(String routeId, String direction) {
        Objects.requireNonNull(routeId);

        Objects.requireNonNull(direction);

        Key key = Key.builder()
                     .partitionValue(routeId)
                     .sortValue(direction)
                     .build();

        RouteStops item = this.stops.getItem(key);

        if (item == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        List<app.cta4j.busroute.model.Stop> stops = item.getStops();

        if ((stops == null) || stops.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return stops.stream()
                    .map(Stop::from)
                    .toList();
    }
}
