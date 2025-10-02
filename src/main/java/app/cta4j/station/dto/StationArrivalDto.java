package app.cta4j.station.dto;

import app.cta4j.model.TrainRoute;
import com.fasterxml.jackson.annotation.JsonGetter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

public record StationArrivalDto(
    int stationId,

    int stopId,

    String stationName,

    String stopDescription,

    int run,

    TrainRoute route,

    int destinationStopId,

    String destinationName,

    int direction,

    Instant predictionTime,

    Instant arrivalTime,

    boolean approaching,

    boolean scheduled,

    boolean delayed,

    boolean faulted,

    String flags,

    BigDecimal latitude,

    BigDecimal longitude,

    int heading
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
