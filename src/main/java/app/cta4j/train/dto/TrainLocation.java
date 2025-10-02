package app.cta4j.train.dto;

import java.util.List;

public record TrainLocation(
    Coordinates coordinates,

    List<UpcomingArrival> arrivals
) {
}
