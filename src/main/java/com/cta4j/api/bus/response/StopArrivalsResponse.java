package com.cta4j.api.bus.response;

import com.cta4j.api.bus.dto.StopArrivalDto;

import java.util.List;

public record StopArrivalsResponse(List<StopArrivalDto> arrivals) {
    public StopArrivalsResponse {
        arrivals = List.copyOf(arrivals);
    }
}
