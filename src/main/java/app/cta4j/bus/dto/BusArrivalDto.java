package app.cta4j.bus.dto;

import app.cta4j.bus.dto.serialization.StringToInstantDeserializer;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.lang.NonNull;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;

@Schema(
    name = "BusArrival",
    description = "Represents a predicted CTA bus arrival at a specific stop."
)
public record BusArrivalDto(
    @Schema(
        description = "Type of prediction for this stop (arrival or departure), as provided in the CTA data feed.",
        example = "ARRIVAL"
    )
    @JsonAlias("typ")
    @NonNull
    BusPredictionType predictionType,

    @Schema(
        description = "Unique identifier for the CTA bus stop, as provided in the official CTA data feed.",
        example = "18447"
    )
    @JsonAlias("stpid")
    @NonNull
    String stopId,

    @Schema(
        description = "Name of the stop where the bus will arrive.",
        example = "Dearborn & Chicago"
    )
    @JsonAlias("stpnm")
    @NonNull
    String stopName,

    @Schema(
        description = "Unique ID of the vehicle associated with this prediction.",
        example = "8839"
    )
    @JsonAlias("vid")
    int vehicleId,

    @Schema(
        description = "Linear distance remaining to the stop, in feet.",
        example = "8504"
    )
    @JsonAlias("dstp")
    @NonNull
    BigInteger distanceToStop,

    @Schema(
        description = "Alphanumeric route designator.",
        example = "22"
    )
    @JsonAlias("rt")
    @NonNull
    String route,

    @Schema(
        description = "Direction of travel for the route, as provided in the CTA data feed.",
        example = "Northbound"
    )
    @JsonAlias("rtdir")
    @NonNull
    String routeDirection,

    @Schema(
        description = "Final destination for the bus associated with this prediction.",
        example = "Howard"
    )
    @JsonAlias("des")
    @NonNull
    String destination,

    @Schema(
        description = "Predicted time when the bus will arrive at the stop, in UTC.",
        example = "2025-08-14T20:35:00Z",
        type = "string",
        format = "date-time"
    )
    @JsonDeserialize(using = StringToInstantDeserializer.class)
    @JsonAlias("prdtm")
    @NonNull
    Instant arrivalTime,

    @Schema(
        description = "Indicates whether the bus is delayed beyond its scheduled or predicted arrival.",
        example = "false"
    )
    @JsonAlias("dly")
    boolean delayed,

    @Schema(
        description = "Indicates whether a dynamic action applies to this prediction.",
        example = "false"
    )
    @JsonAlias("dyn")
    boolean dynamicActions,

    @Schema(
        description = "Service zone name if the bus is within a defined zone; otherwise empty."
    )
    @NonNull
    String zone,

    @Schema(
        description = "Real-time passenger load category reported for the bus.",
        example = "HALF_EMPTY"
    )
    @JsonAlias("psgld")
    @NonNull
    PassengerLoad passengerLoad,

    @Schema(
        description = "Flag-stop behavior for this prediction.",
        example = "NORMAL"
    )
    @JsonAlias("flagstop")
    @NonNull
    FlagStop flagStop
) {
    private static long minutesBetween(Instant from, Instant to) {
        long mins = Duration.between(from, to)
                            .toMinutes();

        return Math.max(mins, 0L);
    }

    @JsonGetter("etaMinutes")
    @Schema(
        description = "Minutes until arrival, rounded down. 0 means Due.",
        example = "3"
    )
    public Long etaMinutes() {
        Instant now = Instant.now();

        return minutesBetween(now, this.arrivalTime);
    }

    @JsonGetter("etaLabel")
    @Schema(
        description = "Human-friendly ETA: 'Due' when ≤ 1 minute, otherwise 'Xm'.",
        example = "Due"
    )
    public String etaLabel() {
        Instant now = Instant.now();

        long mins = minutesBetween(now, this.arrivalTime);

        return (mins <= 1) ? "Due" : mins + "m";
    }
}
