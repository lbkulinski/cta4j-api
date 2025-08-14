package app.cta4j.bus.dto;

import app.cta4j.bus.dto.serialization.StringToInstantDeserializer;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigInteger;
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
    BusPredictionType predictionType,

    @Schema(
        description = "Unique identifier for the CTA bus stop, as provided in the official CTA data feed.",
        example = "18447"
    )
    @JsonAlias("stpid")
    String stopId,

    @Schema(
        description = "Name of the stop where the bus will arrive.",
        example = "Dearborn & Chicago"
    )
    @JsonAlias("stpnm")
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
    BigInteger distanceToStop,

    @Schema(
        description = "Alphanumeric route designator.",
        example = "22"
    )
    @JsonAlias("rt")
    String route,

    @Schema(
        description = "Direction of travel for the route, as provided in the CTA data feed.",
        example = "Northbound"
    )
    @JsonAlias("rtdir")
    String routeDirection,

    @Schema(
        description = "Final destination for the bus associated with this prediction.",
        example = "Howard"
    )
    @JsonAlias("des")
    String destination,

    @Schema(
        description = "Predicted time when the bus will arrive at the stop, in UTC.",
        example = "2025-08-14T20:35:00Z",
        type = "string",
        format = "date-time"
    )
    @JsonDeserialize(using = StringToInstantDeserializer.class)
    @JsonAlias("prdtm")
    Instant predictionTime,

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
    String zone,

    @Schema(
        description = "Real-time passenger load category reported for the bus.",
        example = "HALF_EMPTY"
    )
    @JsonAlias("psgld")
    PassengerLoad passengerLoad,

    @Schema(
        description = "Flag-stop behavior for this prediction.",
        example = "NORMAL"
    )
    @JsonAlias("flagstop")
    FlagStop flagStop
) {
}
