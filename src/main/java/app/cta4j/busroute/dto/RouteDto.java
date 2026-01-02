package app.cta4j.busroute.dto;

public record RouteDto(
    String id,

    String name
) {
    public RouteDto {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }

        if (name == null) {
            throw new IllegalArgumentException("name must not be null");
        }
    }
}
