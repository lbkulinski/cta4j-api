package app.cta4j.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record StationArrivalResponseDto(
    @JsonAlias("ctatt")
    StationArrivalBodyDto body
) {
}
