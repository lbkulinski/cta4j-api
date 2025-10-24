package app.cta4j.bus.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Objects;

public record Bus(
    @NotNull
    String id,

    @NotNull
    String route,

    @NotNull
    String destination,

    @NotNull
    Boolean delayed,

    @NotNull
    BusCoordinates coordinates,

    @NotNull
    List<UpcomingBusArrival> arrivals
) {
    public Bus {
        Objects.requireNonNull(id);

        Objects.requireNonNull(route);

        Objects.requireNonNull(destination);

        Objects.requireNonNull(delayed);

        Objects.requireNonNull(coordinates);

        Objects.requireNonNull(arrivals);
    }
}
