package com.cta4j.api.bus.common.mapper;

import org.mapstruct.Named;

public final class Qualifiers {
    private Qualifiers() {
        throw new UnsupportedOperationException("Utility class");
    }

    @Named("mapBoolean")
    public static boolean mapBoolean(Boolean bool) {
        return bool != null && bool;
    }
}
