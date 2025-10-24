package app.cta4j.busroute.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.validation.constraints.NotNull;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

public record StopArrival(
    @NotNull
    BusPredictionType predictionType,

    @NotNull
    String stopName,

    @NotNull
    String stopId,

    @NotNull
    String vehicleId,

    @NotNull
    BigInteger distanceToStop,

    @NotNull
    String route,

    @NotNull
    String routeDesignator,

    @NotNull
    String routeDirection,

    @NotNull
    String destination,

    @NotNull
    Instant arrivalTime,

    @NotNull
    Boolean delayed
) {
    public StopArrival {
        Objects.requireNonNull(predictionType);

        Objects.requireNonNull(stopName);

        Objects.requireNonNull(stopId);

        Objects.requireNonNull(vehicleId);

        Objects.requireNonNull(distanceToStop);

        Objects.requireNonNull(route);

        Objects.requireNonNull(routeDesignator);

        Objects.requireNonNull(routeDirection);

        Objects.requireNonNull(destination);

        Objects.requireNonNull(arrivalTime);

        Objects.requireNonNull(delayed);
    }

    private static long minutesBetween(Instant from, Instant to) {
        long mins = Duration.between(from, to)
                            .toMinutes();

        return Math.max(mins, 0L);
    }

    @NotNull
    @JsonGetter("etaMinutes")
    public Long etaMinutes() {
        Instant now = Instant.now();

        return minutesBetween(now, this.arrivalTime);
    }

    @NotNull
    @JsonGetter("etaLabel")
    public String etaLabel() {
        Instant now = Instant.now();

        long mins = minutesBetween(now, this.arrivalTime);

        return (mins <= 1) ? "Due" : mins + "m";
    }
}
