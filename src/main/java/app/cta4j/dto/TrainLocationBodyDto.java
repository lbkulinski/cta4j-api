package app.cta4j.dto;

import app.cta4j.dto.serialization.StringToInstantDeserializer;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import java.time.Instant;
import java.util.List;

public record TrainLocationBodyDto(
    @JsonDeserialize(using = StringToInstantDeserializer.class)
    @JsonAlias("tmst")
    Instant timestamp,

    @JsonAlias("errCd")
    int errorCode,

    @JsonAlias("errNm")
    String errorMessage,

    @JsonAlias("route")
    List<TrainLocationRouteDto> routes
) {
}
