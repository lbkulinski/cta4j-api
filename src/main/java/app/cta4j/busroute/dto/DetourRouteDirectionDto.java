package app.cta4j.busroute.dto;

public record DetourRouteDirectionDto(
    String route,

    String direction
) {
    public DetourRouteDirectionDto {
        if (route == null) {
            throw new IllegalArgumentException("route must not be null");
        }

        if (direction == null) {
            throw new IllegalArgumentException("direction must not be null");
        }
    }
}
