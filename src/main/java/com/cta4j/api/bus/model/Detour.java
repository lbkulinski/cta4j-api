package com.cta4j.api.bus.model;

import org.jspecify.annotations.NullMarked;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@NullMarked
public record Detour(
    String id,
    String version,
    boolean active,
    String description,
    List<RouteDirection> routeDirections,
    Instant startTime,
    Instant endTime
) {
    public Detour {
        Objects.requireNonNull(id);
        Objects.requireNonNull(version);
        Objects.requireNonNull(description);
        Objects.requireNonNull(startTime);
        Objects.requireNonNull(endTime);

        routeDirections = List.copyOf(routeDirections);
    }

    public record RouteDirection(
        String routeId,
        String direction
    ) {
        public RouteDirection {
            Objects.requireNonNull(routeId);
            Objects.requireNonNull(direction);
        }
    }
}
