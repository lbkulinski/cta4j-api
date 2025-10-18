package app.cta4j.bus.dto;

import java.math.BigDecimal;

public record Bus(
    String id,

    BigDecimal latitude,

    BigDecimal longitude,

    Integer heading,

    String route,

    String destination,

    Boolean delayed
) {}
