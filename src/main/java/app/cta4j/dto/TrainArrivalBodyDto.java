package app.cta4j.dto;

import app.cta4j.dto.serialization.StringToInstantDeserializer;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.Instant;
import java.util.List;

public record TrainArrivalBodyDto(
    @JsonDeserialize(using = StringToInstantDeserializer.class)
    @JsonAlias("tmst")
    Instant timestamp,

    @JsonAlias("errCd")
    int errorCode,

    @JsonAlias("errNm")
    String errorMessage,

    @JsonAlias("eta")
    List<TrainArrivalDto> arrivals
) {
}
