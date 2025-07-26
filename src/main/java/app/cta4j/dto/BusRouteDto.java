package app.cta4j.dto;

import app.cta4j.model.BusRoute;

import java.util.Objects;

public record BusRouteDto(
    String id,
    String name
) {
    public static BusRouteDto from(BusRoute route) {
        Objects.requireNonNull(route);

        return new BusRouteDto(route.getId(), route.getName());
    }
}
