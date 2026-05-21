package com.cta4j.api.bus.response;

import com.cta4j.api.bus.dto.RouteDto;
import org.jspecify.annotations.NullMarked;

import java.util.List;

@NullMarked
public record RoutesResponse(List<RouteDto> routes) {
    public RoutesResponse {
        routes = List.copyOf(routes);
    }
}
