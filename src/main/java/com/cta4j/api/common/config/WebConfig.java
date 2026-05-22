package com.cta4j.api.common.config;

import org.jspecify.annotations.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
    @Bean
    public WebMvcConfigurer webMvcConfigurer(CorsProperties corsProperties) {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                String[] allowedOrigins = corsProperties.allowedOrigins()
                                                        .toArray(String[]::new);

                String[] allowedMethods = corsProperties.allowedMethods()
                                                        .toArray(String[]::new);

                String[] allowedHeaders = corsProperties.allowedHeaders()
                                                        .toArray(String[]::new);

                registry.addMapping("/**")
                        .allowedOrigins(allowedOrigins)
                        .allowedMethods(allowedMethods)
                        .allowedHeaders(allowedHeaders)
                        .allowCredentials(false)
                        .maxAge(3600);
            }
        };
    }
}
