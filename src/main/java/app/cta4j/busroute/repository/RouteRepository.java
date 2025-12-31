package app.cta4j.busroute.repository;

import app.cta4j.busroute.dto.RouteDto;
import app.cta4j.busroute.mapper.RouteMapper;
import app.cta4j.busroute.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;

@Repository
public class RouteRepository {
    private final DynamoDbTable<Route> routes;
    private final RouteMapper routeMapper;

    @Autowired
    public RouteRepository(
        DynamoDbEnhancedClient dynamoDbClient,
        RouteMapper routeMapper,
        @Value("${app.aws.dynamodb.tables.routes}") String tableName
    ) {
        TableSchema<Route> schema = TableSchema.fromImmutableClass(Route.class);

        this.routes = dynamoDbClient.table(tableName, schema);
        this.routeMapper = routeMapper;
    }

    @Cacheable("routes")
    public List<RouteDto> findAll() {
        return routes.scan()
                     .items()
                     .stream()
                     .map(this.routeMapper::toDomain)
                     .toList();
    }
}
