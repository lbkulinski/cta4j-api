package com.cta4j.api.bus.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public record Stop(
    @NotNull
    String id,

    @NotNull
    String name
) {
    public Stop {
        Objects.requireNonNull(id);

        Objects.requireNonNull(name);
    }
}
