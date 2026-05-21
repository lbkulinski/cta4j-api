package com.cta4j.api.aws.config;

import jakarta.validation.constraints.NotEmpty;
import org.jspecify.annotations.NullMarked;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "app.aws.dynamodb.tables")
@Validated
@NullMarked
public record DynamoDbTableProperties(
    @NotEmpty String routes,
    @NotEmpty String routeDirections,
    @NotEmpty String routeStops,
    @NotEmpty String stations,
    @NotEmpty String stops
) {}
