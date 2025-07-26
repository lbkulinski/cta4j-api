package app.cta4j.service;

import app.cta4j.dto.BusRouteDto;
import app.cta4j.dto.BusStopDto;
import app.cta4j.model.BusRoute;
import app.cta4j.model.BusRouteDirections;
import app.cta4j.model.BusRouteStops;
import app.cta4j.model.BusStop;
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
public class BusRouteService {
    private final DynamoDbTable<BusRoute> routes;

    private final DynamoDbTable<BusRouteDirections> routeDirections;

    private final DynamoDbTable<BusRouteStops> routeStops;

    @Autowired
    public BusRouteService(Environment env, DynamoDbEnhancedClient dynamoDbClient) {
        Objects.requireNonNull(env);

        Objects.requireNonNull(dynamoDbClient);

        String routesTableName = env.getRequiredProperty("app.aws.dynamodb.tables.routes");

        TableSchema<BusRoute> routesSchema = TableSchema.fromImmutableClass(BusRoute.class);

        this.routes = dynamoDbClient.table(routesTableName, routesSchema);

        String directionsTableName = env.getRequiredProperty("app.aws.dynamodb.tables.route-directions");

        TableSchema<BusRouteDirections> directionsSchema = TableSchema.fromImmutableClass(BusRouteDirections.class);

        this.routeDirections = dynamoDbClient.table(directionsTableName, directionsSchema);

        String stopsTableName = env.getRequiredProperty("app.aws.dynamodb.tables.route-stops");

        TableSchema<BusRouteStops> stopsSchema = TableSchema.fromImmutableClass(BusRouteStops.class);

        this.routeStops = dynamoDbClient.table(stopsTableName, stopsSchema);
    }

    @Cacheable("busRoutes")
    public List<BusRouteDto> getRoutes() {
        return this.routes.scan()
                          .items()
                          .stream()
                          .map(BusRouteDto::from)
                          .toList();
    }

    @Cacheable("busDirections")
    public List<String> getDirections(String routeId) {
        Objects.requireNonNull(routeId);

        Key key = Key.builder()
                     .partitionValue(routeId)
                     .build();

        BusRouteDirections item = this.routeDirections.getItem(key);

        if (item == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        List<String> directions = item.getDirections();

        if ((directions == null) || directions.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return List.copyOf(directions);
    }

    @Cacheable("busStops")
    public List<BusStopDto> getStops(String routeId, String direction) {
        Objects.requireNonNull(routeId);

        Objects.requireNonNull(direction);

        Key key = Key.builder()
                     .partitionValue(routeId)
                     .sortValue(direction)
                     .build();

        BusRouteStops item = this.routeStops.getItem(key);

        if (item == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        List<BusStop> stops = item.getStops();

        if ((stops == null) || stops.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return stops.stream()
                    .map(BusStopDto::from)
                    .toList();
    }
}
