package com.cta4j.api.bus.response;

import com.cta4j.api.bus.dto.VehicleDto;

import java.util.Objects;

public record VehicleResponse(VehicleDto vehicle) {
    public VehicleResponse {
        Objects.requireNonNull(vehicle);
    }
}
