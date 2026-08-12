package com.cta4j.api.bus.detour.exception;

public final class InvalidDetourRequestException extends RuntimeException {
    public InvalidDetourRequestException() {
        super("direction cannot be provided without routeId");
    }
}
