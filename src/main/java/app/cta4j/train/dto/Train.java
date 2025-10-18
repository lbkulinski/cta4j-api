package app.cta4j.train.dto;

import java.util.List;

public record Train(
    TrainCoordinates coordinates,

    List<UpcomingTrainArrival> arrivals
) {
}
