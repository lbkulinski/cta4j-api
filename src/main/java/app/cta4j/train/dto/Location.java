package app.cta4j.train.dto;

import java.util.List;

public record Location(
    LocationCoordinates coordinates,

    List<LocationArrival> arrivals
) {
}
