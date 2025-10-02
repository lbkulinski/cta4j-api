package app.cta4j.train.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record Coordinates(
    BigDecimal latitude,

    BigDecimal longitude,

    int heading
) {
}
