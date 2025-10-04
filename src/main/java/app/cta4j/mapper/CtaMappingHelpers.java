package app.cta4j.mapper;

import app.cta4j.busroute.dto.BusPredictionType;
import app.cta4j.busroute.dto.FlagStop;
import app.cta4j.busroute.dto.PassengerLoad;
import app.cta4j.model.TrainRoute;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public final class CtaMappingHelpers {
    private static final DateTimeFormatter TRAIN_FORMATTER;

    private static final DateTimeFormatter BUS_FORMATTER;

    private static final ZoneId CENTRAL;

    static {
        TRAIN_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        BUS_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm");

        CENTRAL = ZoneId.of("America/Chicago");
    }

    private CtaMappingHelpers() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    @Named("toInt")
    public static int toInt(String s) {
        if ((s == null) || s.isBlank()) {
            return 0;
        }

        return Integer.parseInt(s);
    }

    @Named("toBigInteger")
    public static BigInteger toBigInteger(String s) {
        if ((s == null) || s.isBlank()) {
            return null;
        }

        return new BigInteger(s);
    }

    @Named("toBigDecimal")
    public static BigDecimal toBigDecimal(String s) {
        if ((s == null) || s.isBlank()) {
            return null;
        }

        return new BigDecimal(s);
    }

    @Named("toBoolean01")
    public static boolean toBoolean01(String s) {
        return "1".equals(s);
    }

    @Named("toTrainInstant")
    public static Instant toTrainInstant(String s) {
        if ((s == null) || s.isBlank()) {
            return null;
        }

        LocalDateTime local = LocalDateTime.parse(s, TRAIN_FORMATTER);

        ZonedDateTime zoned = local.atZone(CENTRAL);

        return zoned.toInstant();
    }

    @Named("toBusInstant")
    public static Instant toBusInstant(String s) {
        if ((s == null) || s.isBlank()) {
            return null;
        }

        LocalDateTime local = LocalDateTime.parse(s, BUS_FORMATTER);

        ZonedDateTime zoned = local.atZone(CENTRAL);

        return zoned.toInstant();
    }

    @Named("toTrainRoute")
    public static TrainRoute toTrainRoute(String s) {
        if ((s == null) || s.isBlank()) {
            return null;
        }

        String upperCase = s.toUpperCase();

        return switch (upperCase) {
            case "RED", "RED LINE" -> TrainRoute.RED;
            case "BLUE", "BLUE LINE" -> TrainRoute.BLUE;
            case "BRN", "BROWN LINE" -> TrainRoute.BROWN;
            case "G", "GREEN LINE" -> TrainRoute.GREEN;
            case "ORG", "ORANGE LINE" -> TrainRoute.ORANGE;
            case "P", "PURPLE LINE" -> TrainRoute.PURPLE;
            case "PINK", "PINK LINE" -> TrainRoute.PINK;
            case "Y", "YELLOW LINE" -> TrainRoute.YELLOW;
            case "N/A" -> TrainRoute.N_A;
            default -> {
                String message = "A route with the name \"%s\" does not exist".formatted(s);

                throw new IllegalArgumentException(message);
            }
        };
    }

    @Named("toBusPredictionType")
    public static BusPredictionType toBusPredictionType(String s) {
        if ((s == null) || s.isBlank()) {
            return null;
        }

        String upperCase = s.toUpperCase();

        return switch (upperCase) {
            case "A" -> BusPredictionType.ARRIVAL;
            case "D" -> BusPredictionType.DEPARTURE;
            default -> {
                String message = "A prediction type with the name \"%s\" does not exist".formatted(s);

                throw new IllegalArgumentException(message);
            }
        };
    }

    @Named("toBusPassengerLoad")
    public static PassengerLoad toBusPassengerLoad(String s) {
        if ((s == null) || s.isBlank()) {
            return null;
        }

        String upperCase = s.toUpperCase();

        return switch (upperCase) {
            case "FULL" -> PassengerLoad.FULL;
            case "HALF_EMPTY" -> PassengerLoad.HALF_EMPTY;
            case "EMPTY" -> PassengerLoad.EMPTY;
            default -> null;
        };
    }

    @Named("toBusFlagStop")
    public static FlagStop toBusFlagStop(String s) {
        if ((s == null) || s.isBlank()) {
            return null;
        }

        return switch (s) {
            case "-1" -> FlagStop.UNDEFINED;
            case "0" -> FlagStop.NORMAL;
            case "1" -> FlagStop.PICKUP_AND_DISCHARGE;
            case "2" -> FlagStop.ONLY_DISCHARGE;
            default -> throw new IllegalArgumentException("Unknown FlagStop value: " + s);
        };
    }
}
