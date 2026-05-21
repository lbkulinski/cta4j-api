package com.cta4j.api.bus.dto;

import org.jspecify.annotations.NullMarked;

import java.util.Objects;

@NullMarked
public record RouteStopDto(
    String id,
    String name
) {
    public RouteStopDto {
        Objects.requireNonNull(id);
        Objects.requireNonNull(name);
    }
}
