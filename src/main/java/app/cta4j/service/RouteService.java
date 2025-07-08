package app.cta4j.service;

import app.cta4j.dto.RouteDto;
import app.cta4j.dto.StopDto;
import app.cta4j.model.Route;
import app.cta4j.model.RouteDirections;
import app.cta4j.model.RouteStops;
import app.cta4j.model.Stop;
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
public class RouteService {
    private final DynamoDbTable<Route> routes;

    private final DynamoDbTable<RouteDirections> routeDirections;

    private final DynamoDbTable<RouteStops> routeStops;

    @Autowired
    public RouteService(Environment env, DynamoDbEnhancedClient dynamoDbClient) {
        Objects.requireNonNull(env);

        Objects.requireNonNull(dynamoDbClient);

        String routesTableName = env.getRequiredProperty("app.aws.dynamodb.tables.routes");

        TableSchema<Route> routesSchema = TableSchema.fromImmutableClass(Route.class);

        this.routes = dynamoDbClient.table(routesTableName, routesSchema);

        String directionsTableName = env.getRequiredProperty("app.aws.dynamodb.tables.route-directions");

        TableSchema<RouteDirections> directionsSchema = TableSchema.fromImmutableClass(RouteDirections.class);

        this.routeDirections = dynamoDbClient.table(directionsTableName, directionsSchema);

        String stopsTableName = env.getRequiredProperty("app.aws.dynamodb.tables.route-stops");

        TableSchema<RouteStops> stopsSchema = TableSchema.fromImmutableClass(RouteStops.class);

        this.routeStops = dynamoDbClient.table(stopsTableName, stopsSchema);
    }

    @Cacheable("routes")
    public List<RouteDto> getRoutes() {
        return this.routes.scan()
                          .items()
                          .stream()
                          .map(RouteDto::from)
                          .toList();
    }

    @Cacheable("directions")
    public List<String> getDirections(String routeId) {
        Objects.requireNonNull(routeId);

        Key key = Key.builder()
                     .partitionValue(routeId)
                     .build();

        RouteDirections item = this.routeDirections.getItem(key);

        if (item == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        List<String> directions = item.getDirections();

        if ((directions == null) || directions.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return List.copyOf(directions);
    }

    @Cacheable("stops")
    public List<StopDto> getStops(String routeId, String direction) {
        Objects.requireNonNull(routeId);

        Objects.requireNonNull(direction);

        Key key = Key.builder()
                     .partitionValue(routeId)
                     .sortValue(direction)
                     .build();

        RouteStops item = this.routeStops.getItem(key);

        if (item == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        List<Stop> stops = item.getStops();

        if ((stops == null) || stops.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return stops.stream()
                    .map(StopDto::from)
                    .toList();
    }
}
