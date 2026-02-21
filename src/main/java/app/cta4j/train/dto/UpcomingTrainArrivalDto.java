package app.cta4j.train.dto;

import app.cta4j.common.model.TrainRoute;
import com.fasterxml.jackson.annotation.JsonGetter;

import java.time.Duration;
import java.time.Instant;

public record UpcomingTrainArrivalDto(
    String stationId,

    String stopId,
    String stationName,

    String stopDescription,

    String run,

    TrainRoute route,

    String destinationStopId,

    String destinationName,

    Integer direction,

    Instant predictionTime,

    Instant arrivalTime,

    Boolean approaching,

    Boolean scheduled,

    Boolean delayed,

    Boolean faulted,

    String flags
) {
    public UpcomingTrainArrivalDto {
        if (stationId == null) {
            throw new IllegalArgumentException("stationId must not be null");
        }

        if (stopId == null) {
            throw new IllegalArgumentException("stopId must not be null");
        }

        if (stationName == null) {
            throw new IllegalArgumentException("stationName must not be null");
        }

        if (stopDescription == null) {
            throw new IllegalArgumentException("stopDescription must not be null");
        }

        if (run == null) {
            throw new IllegalArgumentException("run must not be null");
        }

        if (route == null) {
            throw new IllegalArgumentException("route must not be null");
        }

        if (destinationStopId == null) {
            throw new IllegalArgumentException("destinationStopId must not be null");
        }

        if (destinationName == null) {
            throw new IllegalArgumentException("destinationName must not be null");
        }

        if (direction == null) {
            throw new IllegalArgumentException("direction must not be null");
        }

        if (predictionTime == null) {
            throw new IllegalArgumentException("predictionTime must not be null");
        }

        if (arrivalTime == null) {
            throw new IllegalArgumentException("arrivalTime must not be null");
        }

        if (approaching == null) {
            throw new IllegalArgumentException("approaching must not be null");
        }

        if (scheduled == null) {
            throw new IllegalArgumentException("scheduled must not be null");
        }

        if (delayed == null) {
            throw new IllegalArgumentException("delayed must not be null");
        }

        if (faulted == null) {
            throw new IllegalArgumentException("faulted must not be null");
        }
    }

    @JsonGetter("etaMinutes")
    public Long etaMinutes() {
        return minutesBetween(this.predictionTime, this.arrivalTime);
    }

    @JsonGetter("etaLabel")
    public String etaLabel() {
        long mins = minutesBetween(this.predictionTime, this.arrivalTime);

        return (mins <= 1) ? "Due" : mins + "m";
    }

    private static long minutesBetween(Instant from, Instant to) {
        long mins = Duration.between(from, to)
                            .toMinutes();

        return Math.max(mins, 0L);
    }
}
