package app.cta4j.train.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Train(
    TrainCoordinates coordinates,

    @NotNull
    List<UpcomingTrainArrival> arrivals
) {
    public Train {
        Objects.requireNonNull(arrivals);
    }
}
