package app.cta4j.bus.dto;

import java.math.BigDecimal;

public record Vehicle(
    String id,

    BigDecimal latitude,

    BigDecimal longitude,

    int heading,

    String route,

    String destination,

    boolean delayed
) {
}
