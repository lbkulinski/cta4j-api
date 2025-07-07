package app.cta4j.service;

import app.cta4j.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;
import java.util.Objects;

@Service
public class RouteService {
    private final DynamoDbTable<Route> routes;

    @Autowired
    public RouteService(Environment env, DynamoDbEnhancedClient dynamoDbClient) {
        Objects.requireNonNull(env);

        Objects.requireNonNull(dynamoDbClient);

        String tableName = env.getRequiredProperty("app.aws.dynamodb.tables.routes");

        TableSchema<Route> tableSchema = TableSchema.fromImmutableClass(Route.class);

        this.routes = dynamoDbClient.table(tableName, tableSchema);
    }

    @Cacheable("routes")
    public List<Route> getRoutes() {
        return this.routes.scan()
                          .items()
                          .stream()
                          .toList();
    }
}
