package app.cta4j.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;

public enum PredictionType {
    ARRIVAL,

    DEPARTURE;

    @JsonCreator
    public static PredictionType parseString(String string) {
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
