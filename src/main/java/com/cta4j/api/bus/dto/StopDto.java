package com.cta4j.api.bus.dto;

import org.jspecify.annotations.NullMarked;

import java.math.BigDecimal;
import java.util.Objects;

@NullMarked
public record StopDto(
    String id,
    String name,
    BigDecimal latitude,
    BigDecimal longitude
) {
    public StopDto {
        Objects.requireNonNull(id);
        Objects.requireNonNull(name);
        Objects.requireNonNull(latitude);
        Objects.requireNonNull(longitude);
    }
}
