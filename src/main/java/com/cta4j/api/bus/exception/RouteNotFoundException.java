package com.cta4j.api.bus.exception;

public final class RouteNotFoundException extends RuntimeException {
    public RouteNotFoundException(String route) {
        super("Route '%s' not found".formatted(route));
    }
}
