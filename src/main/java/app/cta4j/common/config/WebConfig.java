package app.cta4j.common.config;

import app.cta4j.common.config.properties.CorsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final CorsProperties corsProperties;

    @Autowired
    public WebConfig(CorsProperties corsProperties) {
        this.corsProperties = corsProperties;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String[] allowedOrigins = this.corsProperties.getAllowedOrigins()
                                                     .toArray(String[]::new);
        String[] allowedMethods = this.corsProperties.getAllowedMethods()
                                                     .toArray(String[]::new);
        String[] allowedHeaders = this.corsProperties.getAllowedHeaders()
                                                     .toArray(String[]::new);

        registry.addMapping("/**")
                .allowedOrigins(allowedOrigins)
                .allowedMethods(allowedMethods)
                .allowedHeaders(allowedHeaders)
                .allowCredentials(false)
                .maxAge(3600);
    }

    @Override
    public void configureApiVersioning(ApiVersionConfigurer configurer) {
        configurer.useRequestHeader("API-Version");
    }
}
