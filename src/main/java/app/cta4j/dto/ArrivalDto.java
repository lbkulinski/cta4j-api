package app.cta4j.dto;

import app.cta4j.dto.serialization.StringToBooleanDeserializer;
import app.cta4j.dto.serialization.StringToInstantDeserializer;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.math.BigDecimal;
import java.time.Instant;

public record ArrivalDto(
    @JsonAlias("staId")
    String stationId,

    @JsonAlias("staNm")
    String stationName,

    @JsonAlias("stpId")
    String stopId,

    @JsonAlias("stpDe")
    String stopDescription,

    @JsonAlias("rn")
    int run,

    @JsonAlias("rt")
    Route route,

    @JsonAlias("destSt")
    String destinationStopId,

    @JsonAlias("destNm")
    String destinationName,

    @JsonAlias("trDr")
    int direction,

    @JsonDeserialize(using = StringToInstantDeserializer.class)
    @JsonAlias("prdt")
    Instant predictionTime,

    @JsonDeserialize(using = StringToInstantDeserializer.class)
    @JsonAlias("arrT")
    Instant arrivalTime,

    @JsonDeserialize(using = StringToBooleanDeserializer.class)
    @JsonAlias("isApp")
    boolean approaching,

    @JsonDeserialize(using = StringToBooleanDeserializer.class)
    @JsonAlias("isSch")
    boolean scheduled,

    @JsonDeserialize(using = StringToBooleanDeserializer.class)
    @JsonAlias("isFlt")
    boolean faulted,

    @JsonDeserialize(using = StringToBooleanDeserializer.class)
    @JsonAlias("isDly")
    boolean delayed,

    @JsonAlias("lat")
    BigDecimal latitude,

    @JsonAlias("lon")
    BigDecimal longitude,

    @JsonAlias("heading")
    int heading
) {
}
