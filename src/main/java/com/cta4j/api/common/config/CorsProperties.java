package com.cta4j.api.common.config;

import org.jspecify.annotations.NullMarked;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Objects;

@ConfigurationProperties(prefix = "app.cors")
@NullMarked
public record CorsProperties (
    List<String> allowedOrigins,
    List<String> allowedMethods,
    List<String> allowedHeaders
) {
    public CorsProperties {
        Objects.requireNonNull(allowedOrigins, "allowedOrigins must not be null");
        Objects.requireNonNull(allowedMethods, "allowedMethods must not be null");
        Objects.requireNonNull(allowedHeaders, "allowedHeaders must not be null");

        allowedOrigins = List.copyOf(allowedOrigins);
        allowedMethods = List.copyOf(allowedMethods);
        allowedHeaders = List.copyOf(allowedHeaders);
    }
}
