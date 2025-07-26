package app.cta4j.train.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record TrainArrivalResponseDto(
    @JsonAlias("ctatt")
    TrainArrivalBodyDto body
) {
}
