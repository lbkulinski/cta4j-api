package app.cta4j.bus.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PassengerLoad {
    FULL,

    HALF_EMPTY,

    EMPTY;

    @JsonCreator
    public static PassengerLoad fromString(String value) {
        if (value == null) {
            return null;
        }

        value = value.toUpperCase();

        return switch (value) {
            case "FULL" -> FULL;
            case "HALF_EMPTY" -> HALF_EMPTY;
            case "EMPTY" -> EMPTY;
            default -> null;
        };
    }
}
