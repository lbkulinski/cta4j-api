package app.cta4j.bus.dto;

import org.jspecify.annotations.NullMarked;

import java.util.Objects;

@NullMarked
public record BusResponse(
    String id,
    String route,
    String destination,
    boolean delayed,
    BusCoordinates coordinates
) {
    public BusResponse {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(route, "route must not be null");
        Objects.requireNonNull(destination, "destination must not be null");
        Objects.requireNonNull(coordinates, "coordinates must not be null");
    }
}
