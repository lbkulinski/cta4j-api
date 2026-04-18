package com.cta4j.api.bus.exception;

public final class BusNotFoundException extends RuntimeException {
    public BusNotFoundException(String message) {
        super(message);
    }
}
