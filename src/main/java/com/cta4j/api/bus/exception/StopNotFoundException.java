package com.cta4j.api.bus.exception;

public final class StopNotFoundException extends RuntimeException {
    public StopNotFoundException(String id) {
        super("Stop with ID '%s' not found".formatted(id));
    }
}
