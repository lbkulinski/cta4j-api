package app.cta4j.busroute.dto;

public record StopDto(
    String id,

    String name
) {
    public StopDto {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }

        if (name == null) {
            throw new IllegalArgumentException("name must not be null");
        }
    }
}
