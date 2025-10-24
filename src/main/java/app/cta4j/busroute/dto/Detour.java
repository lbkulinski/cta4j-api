package app.cta4j.busroute.dto;

import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

public record Detour(
    @NotNull
    String id,

    @NotNull
    String version,

    @NotNull
    Boolean active,

    @NotNull
    String description,

    @NotNull
    List<DetourRouteDirection> routeDirections,

    @NotNull
    Instant startTime,

    @NotNull
    Instant endTime
) {
    public Detour {
        Objects.requireNonNull(id);

        Objects.requireNonNull(version);

        Objects.requireNonNull(active);

        Objects.requireNonNull(description);

        Objects.requireNonNull(routeDirections);

        Objects.requireNonNull(startTime);

        Objects.requireNonNull(endTime);
    }
}
