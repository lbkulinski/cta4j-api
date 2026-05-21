package com.cta4j.api.common.mapper;

import org.mapstruct.Named;

public final class CtaMappingHelpers {
    private CtaMappingHelpers() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    @Named("toBoolean")
    public static boolean toBoolean(Boolean bool) {
        return bool != null && bool;
    }
}
