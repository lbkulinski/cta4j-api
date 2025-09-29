package app.cta4j.train.dto;

import java.util.List;

public record FollowTrain(
    FollowTrainPosition position,

    List<FollowTrainArrival> arrivals
) {
}
