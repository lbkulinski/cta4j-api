package app.cta4j.train.dto.location;

import java.util.List;

public record TrainLocation(
    Coordinates coordinates,

    List<LocationArrival> arrivals
) {
}
