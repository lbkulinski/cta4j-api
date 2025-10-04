package app.cta4j.busroute.dto;

import java.util.Objects;

public record Stop(
    String id,

    String name
) {
    public static Stop from(app.cta4j.busroute.model.Stop stop) {
        Objects.requireNonNull(stop);

        return new Stop(stop.getId(), stop.getName());
    }
}
