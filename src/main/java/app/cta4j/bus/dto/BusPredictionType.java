package app.cta4j.bus.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;

public enum BusPredictionType {
    ARRIVAL,

    DEPARTURE;

    @JsonCreator
    public static BusPredictionType parseString(String string) {
        Objects.requireNonNull(string);

        string = string.toUpperCase();

        return switch (string) {
            case "A" -> ARRIVAL;
            case "D" -> DEPARTURE;
            default -> {
                String message = "A prediction type with the name \"%s\" does not exist".formatted(string);

                throw new IllegalArgumentException(message);
            }
        };
    }
}
