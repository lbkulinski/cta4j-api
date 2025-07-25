package app.cta4j.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record LocationRouteDto(
    @JsonAlias("@name")
    String name,

    @JsonAlias("train")
    List<LocationTrainDto> trains
) {
}
