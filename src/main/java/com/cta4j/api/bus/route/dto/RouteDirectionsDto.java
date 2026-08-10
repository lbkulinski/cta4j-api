package com.cta4j.api.bus.route.dto;

import org.jspecify.annotations.NullMarked;

import java.util.List;
import java.util.Objects;

@NullMarked
public record RouteDirectionsDto(List<String> directions) {
    public RouteDirectionsDto {
        Objects.requireNonNull(directions);

        directions = List.copyOf(directions);
    }
}
