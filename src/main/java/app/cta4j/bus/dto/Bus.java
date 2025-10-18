package app.cta4j.bus.dto;

import java.util.List;

public record Bus(
    String id,

    String route,

    String destination,

    Boolean delayed,

    BusCoordinates coordinates,

    List<UpcomingBusArrival> arrivals
) {
}
