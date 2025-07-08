package app.cta4j.dto;

import app.cta4j.model.Route;

import java.util.Objects;

public record RouteDto(
    String id,
    String name
) {
    public static RouteDto from(Route route) {
        Objects.requireNonNull(route);

        return new RouteDto(route.getId(), route.getName());
    }
}
