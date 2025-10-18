package app.cta4j.bus.dto;

import java.math.BigDecimal;

public record BusCoordinates(
    BigDecimal longitude,

    BigDecimal latitude,

    Integer heading
) {
}
