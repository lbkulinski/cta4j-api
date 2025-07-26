package app.cta4j.bus.dto;

import app.cta4j.bus.model.BusStop;

import java.util.Objects;

public record BusStopDto(
    String id,
    String name
) {
    public static BusStopDto from(BusStop stop) {
        Objects.requireNonNull(stop);

        return new BusStopDto(stop.getId(), stop.getName());
    }
}
