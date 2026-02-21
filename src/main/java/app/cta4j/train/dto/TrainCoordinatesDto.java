package app.cta4j.train.dto;

import java.math.BigDecimal;

public record TrainCoordinatesDto(
    BigDecimal latitude,

    BigDecimal longitude,

    Integer heading
) {
    public TrainCoordinatesDto {
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
