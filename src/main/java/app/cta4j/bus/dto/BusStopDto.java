package app.cta4j.bus.dto;

import app.cta4j.bus.model.BusStop;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

@Schema(
    name = "BusStop",
    description = "Represents a CTA bus stop with its unique identifier and display name."
)
public record BusStopDto(
    @Schema(
        description = "Unique identifier for the CTA bus stop, as provided in the official CTA data feed.",
        example = "18447",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    String id,

    @Schema(
        description = "Display name of the bus stop, which may include the intersection or landmark name.",
        example = "Dearborn & Chicago",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    String name
) {
    public static BusStopDto from(BusStop stop) {
        Objects.requireNonNull(stop);

        return new BusStopDto(stop.getId(), stop.getName());
    }
}
