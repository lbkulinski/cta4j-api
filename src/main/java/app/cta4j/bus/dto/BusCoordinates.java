package app.cta4j.bus.dto;

import java.math.BigDecimal;

public record BusCoordinates(
    BigDecimal latitude,
    BigDecimal longitude,
    Integer heading
) {
    public BusCoordinates {
        if (latitude == null) {
            throw new IllegalArgumentException("latitude must not be null");
        }

        if (longitude == null) {
            throw new IllegalArgumentException("longitude must not be null");
        }

        if (heading == null) {
            throw new IllegalArgumentException("heading must not be null");
        }
    }
}
