package app.cta4j.bus.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Objects;

public record BusCoordinates(
    @NotNull
    BigDecimal latitude,

    @NotNull
    BigDecimal longitude,

    @NotNull
    Integer heading
) {
    public BusCoordinates {
        Objects.requireNonNull(latitude);

        Objects.requireNonNull(longitude);

        Objects.requireNonNull(heading);
    }
}
