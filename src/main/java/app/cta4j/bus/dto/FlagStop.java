package app.cta4j.bus.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum FlagStop {
    UNDEFINED,
    NORMAL,
    PICKUP_AND_DISCHARGE,
    ONLY_DISCHARGE;

    @JsonCreator
    public static FlagStop fromInteger(int value) {
        return switch (value) {
            case -1 -> UNDEFINED;
            case 0 -> NORMAL;
            case 1 -> PICKUP_AND_DISCHARGE;
            case 2 -> ONLY_DISCHARGE;
            default -> throw new IllegalArgumentException("Unknown FlagStop value: " + value);
        };
    }
}
