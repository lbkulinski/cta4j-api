package app.cta4j.bus.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public record DetourRouteDirection(
    @NotNull
    String route,

    @NotNull
    String direction
) {
    public DetourRouteDirection {
        Objects.requireNonNull(route);

        Objects.requireNonNull(direction);
    }
}
