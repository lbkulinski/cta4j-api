package com.cta4j.api.bus.vehicle.exception;

public final class VehicleNotFoundException extends RuntimeException {
    public VehicleNotFoundException(String vehicleId) {
        super("Vehicle with ID '%s' not found".formatted(vehicleId));
    }
}
