package app.cta4j.station.dto;

import app.cta4j.station.model.Station;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

@Schema(
    name = "Station",
    description = "Represents a CTA train station with its unique identifier and display name."
)
public record StationDto(
    @Schema(
        description = "Unique identifier for the CTA station, as provided in the official CTA data feed.",
        example = "41320",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    int id,

    @Schema(
        description = "Display name of the CTA station, which may include served transit lines in parentheses.",
        example = "Belmont (Red, Brown & Purple lines)",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    String name
) {
    public static StationDto from(Station station) {
        Objects.requireNonNull(station);
        
        return new StationDto(station.getId(), station.getName());
    }
}
