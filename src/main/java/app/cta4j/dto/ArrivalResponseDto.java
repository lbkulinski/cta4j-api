package app.cta4j.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record ArrivalResponseDto(
    @JsonAlias("ctatt")
    ArrivalBodyDto body
) {
}
