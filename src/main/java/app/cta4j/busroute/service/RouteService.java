package app.cta4j.busroute.service;

import app.cta4j.busroute.dto.Route;
import app.cta4j.busroute.mapper.RouteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;

@Service
public class RouteService {
    private final DynamoDbTable<app.cta4j.busroute.model.Route> routes;

    private final RouteMapper routeMapper;

    @Autowired
    public RouteService(Environment env, DynamoDbEnhancedClient dynamoDbClient, RouteMapper routeMapper) {
        String tableName = env.getRequiredProperty("app.aws.dynamodb.tables.routes");

        TableSchema<app.cta4j.busroute.model.Route> schema = TableSchema.fromImmutableClass(app.cta4j.busroute.model.Route.class);

        this.routes = dynamoDbClient.table(tableName, schema);

        this.routeMapper = routeMapper;
    }

    @Cacheable("routes")
    public List<Route> getRoutes() {
        return routes.scan()
                     .items()
                     .stream()
                     .map(this.routeMapper::toDomain)
                     .toList();
    }
}
