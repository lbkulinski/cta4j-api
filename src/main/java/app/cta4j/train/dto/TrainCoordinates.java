package app.cta4j.train.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Objects;

public record TrainCoordinates(
    @NotNull
    BigDecimal latitude,

    @NotNull
    BigDecimal longitude,

    @NotNull
    Integer heading
) {
    public TrainCoordinates {
        Objects.requireNonNull(latitude);

        Objects.requireNonNull(longitude);

        Objects.requireNonNull(heading);
    }
}
