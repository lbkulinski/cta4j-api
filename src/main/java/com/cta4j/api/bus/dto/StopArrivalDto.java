package com.cta4j.api.bus.dto;

import com.cta4j.bus.prediction.model.DynamicAction;
import com.cta4j.bus.prediction.model.PredictionType;
import com.fasterxml.jackson.annotation.JsonGetter;
import org.jspecify.annotations.NullMarked;

import java.time.Instant;

@NullMarked
public record StopArrivalDto(
    PredictionType type,
    String route,
    String direction,
    String destination,
    Instant arrivalTime,
    boolean delayed,
    long etaMinutes,
    DynamicAction dynamicAction
) {
    @JsonGetter("etaLabel")
    public String etaLabel() {
        if (this.etaMinutes <= 1) {
            return "Due";
        }

        return "%d min".formatted(this.etaMinutes);
    }
}
