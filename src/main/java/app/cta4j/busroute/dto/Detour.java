package app.cta4j.busroute.dto;

import java.time.Instant;
import java.util.List;

public record Detour(
    String id,

    int version,

    boolean active,

    String description,

    List<DetourRouteDirection> routeDirections,

    Instant startDate,

    Instant endDate
) {
}
