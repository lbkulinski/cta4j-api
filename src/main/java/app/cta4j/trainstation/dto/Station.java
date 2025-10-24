package app.cta4j.trainstation.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public record Station(
    @NotNull
    String id,

    @NotNull
    String name
) {
    public Station {
        Objects.requireNonNull(id);

        Objects.requireNonNull(name);
    }
}
