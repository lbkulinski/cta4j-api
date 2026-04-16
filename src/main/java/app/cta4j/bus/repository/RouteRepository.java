package app.cta4j.bus.repository;

import app.cta4j.aws.config.DynamoDbTableProperties;
import app.cta4j.bus.model.Route;
import org.jspecify.annotations.NullMarked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;

@Repository
@NullMarked
public class RouteRepository {
    private final DynamoDbTable<Route> routes;

    @Autowired
    public RouteRepository(
        DynamoDbEnhancedClient dynamoDbClient,
        DynamoDbTableProperties tableProperties
    ) {
        TableSchema<Route> schema = TableSchema.fromImmutableClass(Route.class);

        this.routes = dynamoDbClient.table(tableProperties.routes(), schema);
    }

    @Cacheable("routes")
    public List<Route> findAll() {
        return routes.scan()
                     .items()
                     .stream()
                     .toList();
    }
}
