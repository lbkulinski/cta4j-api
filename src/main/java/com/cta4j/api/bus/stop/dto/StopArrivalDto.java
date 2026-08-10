package com.cta4j.api.bus.stop.dto;

import com.cta4j.bus.prediction.model.DynamicAction;
import com.cta4j.bus.prediction.model.PredictionType;
import com.fasterxml.jackson.annotation.JsonGetter;
import org.jspecify.annotations.NullMarked;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

@NullMarked
public record StopArrivalDto(
    PredictionType type,
    String routeId,
    String direction,
    String destination,
    Instant arrivalTime,
    boolean delayed,
    DynamicAction dynamicAction
) {
    public StopArrivalDto {
        Objects.requireNonNull(type);
        Objects.requireNonNull(routeId);
        Objects.requireNonNull(direction);
        Objects.requireNonNull(destination);
        Objects.requireNonNull(arrivalTime);
        Objects.requireNonNull(dynamicAction);
    }

    @JsonGetter("etaMinutes")
    public long etaMinutes() {
        long minutes = Duration.between(Instant.now(), this.arrivalTime).toMinutes();

        return Math.max(minutes, 0L);
    }

    @JsonGetter("etaLabel")
    public String etaLabel() {
        long etaMinutes = this.etaMinutes();

        if (etaMinutes <= 1) {
            return "Due";
        }

        return "%d min".formatted(etaMinutes);
    }
}
