package app.cta4j.bus.dto;

import app.cta4j.bus.dto.serialization.StringToBooleanDeserializer;
import app.cta4j.bus.dto.serialization.StringToInstantDeserializer;
import app.cta4j.bus.model.BusRouteDirections;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.Instant;
import java.util.List;

public record BusDetourDto(
    String id,

    @JsonAlias("ver")
    int version,

    @JsonDeserialize(using = StringToBooleanDeserializer.class)
    @JsonAlias("st")
    boolean active,

    @JsonAlias("desc")
    String description,

    @JsonAlias("rtdirs")
    List<BusRouteDirections> routeDirections,

    @JsonDeserialize(using = StringToInstantDeserializer.class)
    @JsonAlias("startdt")
    Instant startDate,

    @JsonDeserialize(using = StringToInstantDeserializer.class)
    @JsonAlias("enddt")
    Instant endDate
) {
}
