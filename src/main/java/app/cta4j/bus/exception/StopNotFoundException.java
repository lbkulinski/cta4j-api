package app.cta4j.bus.exception;

public final class StopNotFoundException extends RuntimeException {
    public StopNotFoundException(String message) {
        super(message);
    }
}
