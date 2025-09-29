package app.cta4j.train.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

@Schema(
    name = "TrainStation",
    description = "Represents a CTA train station with its unique identifier and display name."
)
public record TrainStation(
    @Schema(
        description = "Unique identifier for the CTA station, as provided in the official CTA data feed.",
        example = "41320",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    int id,

    @Schema(
        description = "Display name of the station, which may include served transit lines in parentheses.",
        example = "Belmont (Red, Brown & Purple lines)",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    String name
) {
    public static TrainStation from(app.cta4j.train.model.TrainStation station) {
        Objects.requireNonNull(station);
        
        return new TrainStation(station.getId(), station.getName());
    }
}
