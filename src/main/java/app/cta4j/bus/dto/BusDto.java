package app.cta4j.bus.dto;

import java.util.List;

public record BusDto(
    String id,

    String route,

    String destination,

    Boolean delayed,

    BusCoordinatesDto coordinates,

    List<UpcomingBusArrivalDto> arrivals
) {
    public BusDto {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }

        if (route == null) {
            throw new IllegalArgumentException("route must not be null");
        }

        if (destination == null) {
            throw new IllegalArgumentException("destination must not be null");
        }

        if (delayed == null) {
            throw new IllegalArgumentException("delayed must not be null");
        }

        if (coordinates == null) {
            throw new IllegalArgumentException("coordinates must not be null");
        }

        if (arrivals == null) {
            throw new IllegalArgumentException("arrivals must not be null");
        }

        for (UpcomingBusArrivalDto arrival : arrivals) {
            if (arrival == null) {
                throw new IllegalArgumentException("arrivals must not contain null elements");
            }
        }
    }
}
