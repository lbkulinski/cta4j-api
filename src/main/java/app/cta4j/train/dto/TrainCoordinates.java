package app.cta4j.train.dto;

import java.math.BigDecimal;

public record TrainCoordinates(
    BigDecimal latitude,

    BigDecimal longitude,

    int heading
) {
}
