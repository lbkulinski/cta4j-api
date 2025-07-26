package app.cta4j.bus.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

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
    int routeDirection,

    @JsonAlias("des")
    String destination,

    @JsonAlias("prdtm")
    Instant predictionTime,

    @JsonAlias("dly")
    boolean delayed
) {
}
