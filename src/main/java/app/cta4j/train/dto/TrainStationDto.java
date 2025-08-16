package app.cta4j.train.dto;

import app.cta4j.train.model.TrainStation;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

@Schema(
    name = "TrainStation",
    description = "Represents a CTA train station with its unique identifier and display name."
)
public record TrainStationDto(
    @Schema(
        description = "Unique identifier for the CTA station, as provided in the official CTA data feed.",
        example = "41320"
    )
    String id,

    @Schema(
        description = "Display name of the station, which may include served transit lines in parentheses.",
        example = "Belmont (Red, Brown & Purple lines)"
    )
    String name
) {
    public static TrainStationDto from(TrainStation station) {
        Objects.requireNonNull(station);
        
        return new TrainStationDto(station.getId(), station.getName());
    }
}
