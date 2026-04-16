package app.cta4j.bus.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public record Route(
    @NotNull
    String id,

    @NotNull
    String name
) {
    public Route {
        Objects.requireNonNull(id);

        Objects.requireNonNull(name);
    }
}
