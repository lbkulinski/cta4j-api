package app.cta4j.aws.config;

import org.jspecify.annotations.NullMarked;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Objects;

@ConfigurationProperties(prefix = "app.aws.dynamodb.table")
@NullMarked
public record DynamoDbTableProperties(
    String routes,
    String routeDirections,
    String routeStops,
    String stations
) {
    public DynamoDbTableProperties {
        Objects.requireNonNull(routes, "routes must not be null");
        Objects.requireNonNull(routeDirections, "routeDirections must not be null");
        Objects.requireNonNull(routeStops, "routeStops must not be null");
        Objects.requireNonNull(stations, "stations must not be null");
    }
}
