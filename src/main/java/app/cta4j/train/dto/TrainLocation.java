package app.cta4j.train.dto;

import java.util.List;

public record TrainLocation(
    TrainCoordinates coordinates,

    List<UpcomingTrainArrival> arrivals
) {
}
