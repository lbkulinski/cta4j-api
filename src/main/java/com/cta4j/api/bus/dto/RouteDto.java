package com.cta4j.api.bus.dto;

import org.jspecify.annotations.NullMarked;

import java.util.Objects;

@NullMarked
public record RouteDto(
    String id,
    String designator,
    String hexColor,
    String name
) {
    public RouteDto {
        Objects.requireNonNull(id);
        Objects.requireNonNull(designator);
        Objects.requireNonNull(hexColor);
        Objects.requireNonNull(name);
    }
}
