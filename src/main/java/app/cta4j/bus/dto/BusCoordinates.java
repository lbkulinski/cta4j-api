package app.cta4j.bus.dto;

import java.math.BigDecimal;

public record BusCoordinates(
    BigDecimal latitude,

    BigDecimal longitude,

    Integer heading
) {
}
