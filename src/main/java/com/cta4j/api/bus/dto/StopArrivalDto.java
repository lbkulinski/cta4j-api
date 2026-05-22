package com.cta4j.api.bus.dto;

import com.cta4j.bus.prediction.model.DynamicAction;
import com.cta4j.bus.prediction.model.PredictionType;
import com.fasterxml.jackson.annotation.JsonGetter;
import org.jspecify.annotations.NullMarked;

import java.time.Duration;
import java.time.Instant;

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
    @JsonGetter("etaMinutes")
    public long etaMinutes() {
        Instant now = Instant.now();

        long minutes = Duration.between(now, this.arrivalTime)
                               .toMinutes();

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
