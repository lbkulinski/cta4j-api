package com.cta4j.api.bus.stop.dto;

import org.jspecify.annotations.NullMarked;

import java.util.List;
import java.util.Objects;

@NullMarked
public record StopArrivalsDto(List<StopArrivalDto> arrivals) {
    public StopArrivalsDto {
        Objects.requireNonNull(arrivals);

        arrivals = List.copyOf(arrivals);
    }
}
