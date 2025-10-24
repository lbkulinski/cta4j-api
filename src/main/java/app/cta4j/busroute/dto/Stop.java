package app.cta4j.busroute.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public record Stop(
    @NotNull
    String id,

    @NotNull
    String name
) {
    public Stop {
        Objects.requireNonNull(id);

        Objects.requireNonNull(name);
    }
}
