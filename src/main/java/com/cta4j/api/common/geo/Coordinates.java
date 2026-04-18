package com.cta4j.api.common.geo;

import org.jspecify.annotations.NullMarked;

import java.math.BigDecimal;
import java.util.Objects;

@NullMarked
public record Coordinates(
    BigDecimal latitude,

    BigDecimal longitude,

    int heading
) {
    public Coordinates {
        Objects.requireNonNull(latitude);
        Objects.requireNonNull(longitude);

        if ((latitude.compareTo(GeoConstants.MIN_LATITUDE) < 0) ||
            (latitude.compareTo(GeoConstants.MAX_LATITUDE) > 0)) {
            String message = String.format(
                "latitude must be between %s and %s (inclusive)",
                GeoConstants.MIN_LATITUDE,
                GeoConstants.MAX_LATITUDE
            );

            throw new IllegalArgumentException(message);
        }

        if ((longitude.compareTo(GeoConstants.MIN_LONGITUDE) < 0) ||
            (longitude.compareTo(GeoConstants.MAX_LONGITUDE) > 0)) {
            String message = String.format(
                "longitude must be between %s and %s (inclusive)",
                GeoConstants.MIN_LONGITUDE,
                GeoConstants.MAX_LONGITUDE
            );

            throw new IllegalArgumentException(message);
        }

        if ((heading < GeoConstants.MIN_HEADING) || (heading > GeoConstants.MAX_HEADING)) {
            String message = String.format(
                "heading must be between %d and %d (inclusive)",
                GeoConstants.MIN_HEADING,
                GeoConstants.MAX_HEADING
            );

            throw new IllegalArgumentException(message);
        }
    }
}
