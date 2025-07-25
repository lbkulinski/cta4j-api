package app.cta4j.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record LocationResponseDto (
    @JsonAlias("ctatt")
    LocationBodyDto body
) {
}
