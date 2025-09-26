package app.cta4j.train.dto;

import app.cta4j.train.dto.serialization.StringToInstantDeserializer;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.Instant;
import java.util.List;

public record FollowTrainBodyDto(
    @JsonDeserialize(using = StringToInstantDeserializer.class)
    @JsonAlias("tmst")
    Instant timestamp,

    @JsonAlias("errCd")
    int errorCode,

    @JsonAlias("errNm")
    String errorMessage,

    FollowTrainPositionDto position,

    @JsonAlias("eta")
    List<FollowTrainArrivalDto> arrivals
) {
}
