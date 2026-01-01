package app.cta4j.trainstation.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public record StationDto(
    @NotNull
    String id,

    @NotNull
    String name
) {
    public StationDto {
        Objects.requireNonNull(id);

        Objects.requireNonNull(name);
    }
}
