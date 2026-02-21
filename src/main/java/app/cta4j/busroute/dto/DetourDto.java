package app.cta4j.busroute.dto;

import java.time.Instant;
import java.util.List;

public record DetourDto(
    String id,

    String version,

    Boolean active,

    String description,

    List<DetourRouteDirectionDto> routeDirections,

    Instant startTime,

    Instant endTime
) {
    public DetourDto {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }

        if (version == null) {
            throw new IllegalArgumentException("version must not be null");
        }

        if (active == null) {
            throw new IllegalArgumentException("active must not be null");
        }

        if (description == null) {
            throw new IllegalArgumentException("description must not be null");
        }

        if (routeDirections == null) {
            throw new IllegalArgumentException("routeDirections must not be null");
        }

        for (DetourRouteDirectionDto routeDirectionDto : routeDirections) {
            if (routeDirectionDto == null) {
                throw new IllegalArgumentException("routeDirections must not contain null elements");
            }
        }

        if (startTime == null) {
            throw new IllegalArgumentException("startTime must not be null");
        }

        if (endTime == null) {
            throw new IllegalArgumentException("endTime must not be null");
        }
    }
}
