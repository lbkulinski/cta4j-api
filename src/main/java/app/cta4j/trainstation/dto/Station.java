package app.cta4j.trainstation.dto;

import java.util.Objects;

public record Station(
    String id,

    String name
) {
    public static Station from(app.cta4j.trainstation.model.Station station) {
        Objects.requireNonNull(station);
        
        return new Station(station.getId(), station.getName());
    }
}
