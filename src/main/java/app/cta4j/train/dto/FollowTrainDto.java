package app.cta4j.train.dto;

import java.util.List;

public record FollowTrainDto(
    FollowTrainPositionDto position,

    List<FollowTrainArrivalDto> arrivals
) {
}
