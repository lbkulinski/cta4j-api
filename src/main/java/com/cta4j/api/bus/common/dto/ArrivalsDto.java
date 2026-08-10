package com.cta4j.api.bus.common.dto;

import org.jspecify.annotations.NullMarked;

import java.util.List;
import java.util.Objects;

@NullMarked
public record ArrivalsDto(List<ArrivalDto> arrivals) {
    public ArrivalsDto {
        Objects.requireNonNull(arrivals);

        arrivals = List.copyOf(arrivals);
    }
}
