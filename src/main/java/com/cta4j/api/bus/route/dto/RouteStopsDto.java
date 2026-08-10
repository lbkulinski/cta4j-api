package com.cta4j.api.bus.route.dto;

import org.jspecify.annotations.NullMarked;

import java.util.List;
import java.util.Objects;

@NullMarked
public record RouteStopsDto(List<RouteStopDto> stops) {
    public RouteStopsDto {
        Objects.requireNonNull(stops);

        stops = List.copyOf(stops);
    }
}
