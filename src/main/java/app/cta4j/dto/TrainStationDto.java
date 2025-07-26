package app.cta4j.dto;

import app.cta4j.model.TrainStation;

import java.util.Objects;

public record TrainStationDto(
    String id,
    String name
) {
    public static TrainStationDto from(TrainStation station) {
        Objects.requireNonNull(station);
        
        return new TrainStationDto(station.getId(), station.getName());
    }
}
