package com.cta4j.api.bus.model;

import com.cta4j.bus.prediction.model.DynamicAction;
import com.cta4j.bus.prediction.model.PredictionType;
import org.jspecify.annotations.NullMarked;

import java.time.Instant;
import java.util.Objects;

@NullMarked
public record StopArrival(
    PredictionType type,
    String routeId,
    String direction,
    String destination,
    Instant arrivalTime,
    boolean delayed,
    DynamicAction dynamicAction
) {
    public StopArrival {
        Objects.requireNonNull(type);
        Objects.requireNonNull(routeId);
        Objects.requireNonNull(direction);
        Objects.requireNonNull(destination);
        Objects.requireNonNull(arrivalTime);
        Objects.requireNonNull(dynamicAction);
    }
}
