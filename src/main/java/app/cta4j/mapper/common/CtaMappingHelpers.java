package app.cta4j.mapper.common;

import app.cta4j.model.Route;
import org.mapstruct.Named;

import java.math.BigDecimal;
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
    public static Route toTrainRoute(String s) {
        if ((s == null) || s.isBlank()) {
            return null;
        }

        String upperCase = s.toUpperCase();

        return switch (upperCase) {
            case "RED", "RED LINE" -> Route.RED;
            case "BLUE", "BLUE LINE" -> Route.BLUE;
            case "BRN", "BROWN LINE" -> Route.BROWN;
            case "G", "GREEN LINE" -> Route.GREEN;
            case "ORG", "ORANGE LINE" -> Route.ORANGE;
            case "P", "PURPLE LINE" -> Route.PURPLE;
            case "PINK", "PINK LINE" -> Route.PINK;
            case "Y", "YELLOW LINE" -> Route.YELLOW;
            case "N/A" -> Route.N_A;
            default -> {
                String message = "A route with the name \"%s\" does not exist".formatted(s);

                throw new IllegalArgumentException(message);
            }
        };
    }
}
