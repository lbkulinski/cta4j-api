package app.cta4j.train.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Objects;

public record Train(
    @NotNull
    TrainCoordinates coordinates,

    @NotNull
    List<UpcomingTrainArrival> arrivals
) {
    public Train {
        Objects.requireNonNull(coordinates);

        Objects.requireNonNull(arrivals);
    }
}
