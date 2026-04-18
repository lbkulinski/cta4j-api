package com.cta4j.api.train.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public record Station(
    @NotNull
    String id,

    @NotNull
    String name
) {
    public Station {
        Objects.requireNonNull(id);

        Objects.requireNonNull(name);
    }
}
