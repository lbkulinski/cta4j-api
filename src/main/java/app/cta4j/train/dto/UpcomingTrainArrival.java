package app.cta4j.train.dto;

import app.cta4j.common.model.TrainRoute;
import com.fasterxml.jackson.annotation.JsonGetter;

import java.time.Duration;
import java.time.Instant;

public record UpcomingTrainArrival(
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
    private static long minutesBetween(Instant from, Instant to) {
        long mins = Duration.between(from, to)
                            .toMinutes();

        return Math.max(mins, 0L);
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
}
