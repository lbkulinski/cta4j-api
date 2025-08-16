package app.cta4j.bus.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "BusRouteDirection",
    description = "Represents a travel direction for a specific CTA bus route."
)
public record BusRouteDirectionDto(
    @Schema(
        description = "Unique identifier for the CTA bus route, as provided in the official CTA data feed.",
        example = "22"
    )
    String routeId,

    @Schema(
        description = "Direction of travel for the route, as provided in the official CTA data feed.",
        example = "Northbound"
    )
    String direction
) {
}
