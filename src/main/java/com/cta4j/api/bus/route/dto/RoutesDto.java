package com.cta4j.api.bus.route.dto;

import org.jspecify.annotations.NullMarked;

import java.util.List;
import java.util.Objects;

@NullMarked
public record RoutesDto(List<RouteDto> routes) {
    public RoutesDto {
        Objects.requireNonNull(routes);

        routes = List.copyOf(routes);
    }
}
