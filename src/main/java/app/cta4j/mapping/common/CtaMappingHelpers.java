package app.cta4j.mapping.common;

import org.mapstruct.Named;

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
    public static int toInt(String value) {
        return Integer.parseInt(value);
    }

    @Named("toBoolean01")
    public static boolean toBoolean01(String s) {
        return "1".equals(s);
    }

    @Named("toTrainInstant")
    public static Instant toTrainInstant(String s) {
        LocalDateTime local = LocalDateTime.parse(s, TRAIN_FORMATTER);

        ZonedDateTime zoned = local.atZone(CENTRAL);

        return zoned.toInstant();
    }

    @Named("toBusInstant")
    public static Instant toBusInstant(String s) {
        LocalDateTime local = LocalDateTime.parse(s, BUS_FORMATTER);

        ZonedDateTime zoned = local.atZone(CENTRAL);

        return zoned.toInstant();
    }
}
