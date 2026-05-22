package com.cta4j.api.bus.dto;

import com.cta4j.api.common.geo.Coordinates;
import com.cta4j.bus.prediction.model.PassengerLoad;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.time.Instant;
import java.util.Objects;

@NullMarked
public record VehicleDto(
    String id,
    String routeId,
    String destination,
    Coordinates coordinates,
    boolean delayed,
    @Nullable Instant lastUpdated,
    @Nullable Integer speed,
    PassengerLoad passengerLoad,
    @Nullable String stopId
) {
    public VehicleDto {
        Objects.requireNonNull(id);
        Objects.requireNonNull(routeId);
        Objects.requireNonNull(destination);
        Objects.requireNonNull(coordinates);
        Objects.requireNonNull(passengerLoad);
    }
}
