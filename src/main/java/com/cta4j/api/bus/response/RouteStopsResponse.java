package com.cta4j.api.bus.response;

import com.cta4j.api.bus.dto.RouteStopDto;
import org.jspecify.annotations.NullMarked;

import java.util.List;

@NullMarked
public record RouteStopsResponse(List<RouteStopDto> stops) {
    public RouteStopsResponse {
        stops = List.copyOf(stops);
    }
}
