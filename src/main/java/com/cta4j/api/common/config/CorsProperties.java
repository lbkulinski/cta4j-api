package com.cta4j.api.common.config;

import jakarta.validation.constraints.NotEmpty;
import org.jspecify.annotations.NullMarked;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@ConfigurationProperties(prefix = "app.cors")
@Validated
@NullMarked
public record CorsProperties (
    @NotEmpty List<String> allowedOrigins,
    @NotEmpty List<String> allowedMethods,
    @NotEmpty List<String> allowedHeaders
) {
    public CorsProperties {
        allowedOrigins = List.copyOf(allowedOrigins);
        allowedMethods = List.copyOf(allowedMethods);
        allowedHeaders = List.copyOf(allowedHeaders);
    }
}
