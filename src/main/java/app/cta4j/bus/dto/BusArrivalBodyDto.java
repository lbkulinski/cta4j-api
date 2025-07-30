package app.cta4j.bus.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record BusArrivalBodyDto(
    @JsonAlias("prd")
    List<BusArrivalDto> arrivals
) {
}
