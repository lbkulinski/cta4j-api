package app.cta4j.station.dto;

import app.cta4j.station.model.Station;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

public record StationDto(
    int id,

    String name
) {
    public static StationDto from(Station station) {
        Objects.requireNonNull(station);
        
        return new StationDto(station.getId(), station.getName());
    }
}
