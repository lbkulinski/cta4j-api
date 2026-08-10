package com.cta4j.api.bus.detour.dto;

import org.jspecify.annotations.NullMarked;

import java.util.List;
import java.util.Objects;

@NullMarked
public record DetoursDto(List<DetourDto> detours) {
    public DetoursDto {
        Objects.requireNonNull(detours);

        detours = List.copyOf(detours);
    }
}
