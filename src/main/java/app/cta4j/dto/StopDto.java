package app.cta4j.dto;

import app.cta4j.model.Stop;

import java.util.Objects;

public record StopDto(
    String id,

    String name
) {
    public static StopDto from(Stop stop) {
        Objects.requireNonNull(stop);

        return new StopDto(stop.getId(), stop.getName());
    }
}
