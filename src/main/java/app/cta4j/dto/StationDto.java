package app.cta4j.dto;

import java.util.Objects;

public record StationDto(
    String id,
    String name
) {
    public static StationDto from(app.cta4j.model.Station station) {
        Objects.requireNonNull(station);
        
        return new StationDto(station.getId(), station.getName());
    }
}
