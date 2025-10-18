package app.cta4j.busroute.dto;

import java.util.Objects;

public record Route(
    String id,

    String name
) {
    public static Route from(app.cta4j.busroute.model.Route route) {
        Objects.requireNonNull(route);

        return new Route(route.getId(), route.getName());
    }
}
