package app.cta4j.train.dto;

import app.cta4j.common.model.TrainRoute;
import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.validation.constraints.NotNull;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

public record UpcomingTrainArrival(
    @NotNull
    String stationId,

    @NotNull
    String stopId,

    @NotNull
    String stationName,

    @NotNull
    String stopDescription,

    @NotNull
    String run,

    @NotNull
    TrainRoute route,

    @NotNull
    String destinationStopId,

    @NotNull
    String destinationName,

    @NotNull
    Integer direction,

    @NotNull
    Instant predictionTime,

    @NotNull
    Instant arrivalTime,

    @NotNull
    Boolean approaching,

    @NotNull
    Boolean scheduled,

    @NotNull
    Boolean delayed,

    @NotNull
    Boolean faulted,

    @NotNull
    String flags
) {
    public UpcomingTrainArrival {
        Objects.requireNonNull(stationId);

        Objects.requireNonNull(stopId);

        Objects.requireNonNull(stationName);

        Objects.requireNonNull(stopDescription);

        Objects.requireNonNull(run);

        Objects.requireNonNull(route);

        Objects.requireNonNull(destinationStopId);

        Objects.requireNonNull(destinationName);

        Objects.requireNonNull(direction);

        Objects.requireNonNull(predictionTime);

        Objects.requireNonNull(arrivalTime);

        Objects.requireNonNull(approaching);

        Objects.requireNonNull(scheduled);

        Objects.requireNonNull(delayed);

        Objects.requireNonNull(faulted);

        Objects.requireNonNull(flags);
    }

    private static long minutesBetween(Instant from, Instant to) {
        long mins = Duration.between(from, to)
                            .toMinutes();

        return Math.max(mins, 0L);
    }

    @NotNull
    @JsonGetter("etaMinutes")
    public Long etaMinutes() {
        return minutesBetween(this.predictionTime, this.arrivalTime);
    }

    @NotNull
    @JsonGetter("etaLabel")
    public String etaLabel() {
        long mins = minutesBetween(this.predictionTime, this.arrivalTime);

        return (mins <= 1) ? "Due" : mins + "m";
    }
}
