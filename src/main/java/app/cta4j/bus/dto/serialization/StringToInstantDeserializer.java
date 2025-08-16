package app.cta4j.bus.dto.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public final class StringToInstantDeserializer extends JsonDeserializer<Instant> {
    private static final DateTimeFormatter FORMATTER;

    private static final ZoneId CENTRAL;

    static {
        FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm");

        CENTRAL = ZoneId.of("America/Chicago");
    }

    @Override
    public Instant deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String raw = parser.getText()
                           .trim();

        LocalDateTime local = LocalDateTime.parse(raw, FORMATTER);

        ZonedDateTime zoned = local.atZone(CENTRAL);

        return zoned.toInstant();
    }
}
