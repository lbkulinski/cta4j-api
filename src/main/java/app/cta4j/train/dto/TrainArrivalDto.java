package app.cta4j.train.dto;

import app.cta4j.train.dto.serialization.StringToBooleanDeserializer;
import app.cta4j.train.dto.serialization.StringToInstantDeserializer;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.math.BigDecimal;
import java.time.Instant;

public record TrainArrivalDto(
    @JsonAlias("staId")
    int stationId,

    @JsonAlias("stpId")
    int stopId,

    @JsonAlias("staNm")
    String stationName,

    @JsonAlias("stpDe")
    String stopDescription,

    @JsonAlias("rn")
    int run,

    @JsonAlias("rt")
    TrainRoute route,

    @JsonAlias("destSt")
    int destinationStopId,

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
