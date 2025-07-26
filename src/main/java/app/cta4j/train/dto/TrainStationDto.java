package app.cta4j.train.dto;

import app.cta4j.train.model.TrainStation;

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
