package app.cta4j.busroute.dto;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;

public record StopArrival(
    BusPredictionType predictionType,

    String stopId,

    String stopName,

    int vehicleId,

    BigInteger distanceToStop,

    String route,

    String routeDirection,

    String destination,

    Instant arrivalTime,

    boolean delayed,

    boolean dynamicActions,

    String zone,

    PassengerLoad passengerLoad,

    FlagStop flagStop
) {
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
