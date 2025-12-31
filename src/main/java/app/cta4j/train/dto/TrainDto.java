package app.cta4j.train.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record TrainDto(
    TrainCoordinatesDto coordinates,

    List<UpcomingTrainArrivalDto> arrivals
) {
    public TrainDto {
        if (arrivals == null) {
            throw new IllegalArgumentException("arrivals must not be null");
        }

        for (UpcomingTrainArrivalDto arrival : arrivals) {
            if (arrival == null) {
                throw new IllegalArgumentException("arrivals must not contain null elements");
            }
        }
    }
}
