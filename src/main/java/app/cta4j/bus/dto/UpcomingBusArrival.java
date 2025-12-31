package app.cta4j.bus.dto;

import app.cta4j.busroute.dto.BusPredictionType;
import com.fasterxml.jackson.annotation.JsonGetter;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;

public record UpcomingBusArrival(
    BusPredictionType predictionType,
    String stopName,
    String stopId,
    String vehicleId,
    BigInteger distanceToStop,
    String route,
    String routeDesignator,
    String routeDirection,
    String destination,
    Instant arrivalTime,
    Boolean delayed
) {
    public UpcomingBusArrival {
        if (predictionType == null) {
            throw new IllegalArgumentException("predictionType must not be null");
        }

        if (stopName == null) {
            throw new IllegalArgumentException("stopName must not be null");
        }

        if (stopId == null) {
            throw new IllegalArgumentException("stopId must not be null");
        }

        if (vehicleId == null) {
            throw new IllegalArgumentException("vehicleId must not be null");
        }

        if (distanceToStop == null) {
            throw new IllegalArgumentException("distanceToStop must not be null");
        }

        if (route == null) {
            throw new IllegalArgumentException("route must not be null");
        }

        if (routeDesignator == null) {
            throw new IllegalArgumentException("routeDesignator must not be null");
        }

        if (routeDirection == null) {
            throw new IllegalArgumentException("routeDirection must not be null");
        }

        if (destination == null) {
            throw new IllegalArgumentException("destination must not be null");
        }

        if (arrivalTime == null) {
            throw new IllegalArgumentException("arrivalTime must not be null");
        }

        if (delayed == null) {
            throw new IllegalArgumentException("delayed must not be null");
        }
    }

    private static long minutesBetween(Instant from, Instant to) {
        long mins = Duration.between(from, to)
                            .toMinutes();

        return Math.max(mins, 0L);
    }

    @JsonGetter("etaMinutes")
    public Long etaMinutes() {
        Instant now = Instant.now();

        return minutesBetween(now, this.arrivalTime);
    }

    @JsonGetter("etaLabel")
    public String etaLabel() {
        Instant now = Instant.now();

        long mins = minutesBetween(now, this.arrivalTime);

        return (mins <= 1) ? "Due" : mins + "m";
    }
}
