package com.cta4j.api.bus.response;

import com.cta4j.api.bus.dto.DetourDto;

import java.util.List;

public record DetoursResponse(List<DetourDto> detours) {
    public DetoursResponse {
        detours = List.copyOf(detours);
    }
}
