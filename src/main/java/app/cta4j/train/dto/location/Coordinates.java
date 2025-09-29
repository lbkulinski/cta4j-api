package app.cta4j.train.dto.location;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record Coordinates(
    @Schema(
        description = "Latitude of the train's current location, in decimal degrees.",
        example = "41.9452",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    BigDecimal latitude,

    @Schema(
        description = "Longitude of the train's current location, in decimal degrees.",
        example = "-87.65353",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    BigDecimal longitude,

    @Schema(
        description = "Compass heading of the train in degrees, where 0 is north.",
        example = "178",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    int heading
) {
}
