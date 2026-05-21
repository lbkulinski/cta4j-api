package com.cta4j.api.bus.response;

import org.jspecify.annotations.NullMarked;

import java.util.List;

@NullMarked
public record RouteDirectionsResponse(List<String> directions) {
    public RouteDirectionsResponse {
        directions = List.copyOf(directions);
    }
}
