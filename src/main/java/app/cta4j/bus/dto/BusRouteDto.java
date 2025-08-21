package app.cta4j.bus.dto;

import app.cta4j.bus.model.BusRoute;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

@Schema(
    name = "BusRoute",
    description = "Represents a CTA bus route with its unique identifier and display name."
)
public record BusRouteDto(
    @Schema(
        description = "Unique identifier for the CTA bus route, as provided in the official CTA data feed.",
        example = "22",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    String id,

    @Schema(
        description = "Display name of the bus route, which may include service type or destination details.",
        example = "Clark",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    String name
) {
    public static BusRouteDto from(BusRoute route) {
        Objects.requireNonNull(route);

        return new BusRouteDto(route.getId(), route.getName());
    }
}
