package app.cta4j.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    private static final String[] ALLOWED_ORIGINS;

    private static final String[] ALLOWED_METHODS;

    static {
        ALLOWED_ORIGINS = new String[] {
            "https://cta4j.app",
            "https://www.cta4j.app",
            "https://cta4j.com",
            "https://www.cta4j.com",
            "https://chat.openai.com"
        };

        ALLOWED_METHODS = new String[] {
            "GET",
            "OPTIONS"
        };
    }

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(ALLOWED_ORIGINS)
                .allowedMethods(ALLOWED_METHODS)
                .allowedHeaders("*")
                .allowCredentials(false)
                .maxAge(3600);
    }
}
