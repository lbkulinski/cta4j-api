package app.cta4j.bus.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record BusArrivalResponseDto(
    @JsonAlias("bustime-response")
    BusArrivalBodyDto body
) {
}
