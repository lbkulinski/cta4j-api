package app.cta4j.bus.dto;

import app.cta4j.bus.dto.serialization.StringToInstantDeserializer;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.math.BigDecimal;
import java.time.Instant;

public record BusArrivalDto(
    @JsonAlias("typ")
    BusPredictionType predictionType,

    @JsonAlias("stpid")
    String stopId,

    @JsonAlias("stpnm")
    String stopName,

    @JsonAlias("vid")
    int vehicleId,

    @JsonAlias("dstp")
    BigDecimal distanceToStop,

    @JsonAlias("rt")
    String route,

    @JsonAlias("rtdd")
    String routeDescription,

    @JsonAlias("rtdir")
    String routeDirection,

    @JsonAlias("des")
    String destination,

    @JsonDeserialize(using = StringToInstantDeserializer.class)
    @JsonAlias("prdtm")
    Instant predictionTime,

    @JsonAlias("dly")
    boolean delayed
) {
}
