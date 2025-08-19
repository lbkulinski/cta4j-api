package app.cta4j.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Objects;

@EnableWebMvc
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    private static final String[] ALLOWED_ORIGINS;

    private static final String[] ALLOWED_METHODS;

    private static final String[] ALLOWED_HEADERS;

    static {
        ALLOWED_ORIGINS = new String[] {
            "https://cta4j.app",
            "https://chat.openai.com"
        };

        ALLOWED_METHODS = new String[] {
            "GET",
            "OPTIONS"
        };

        ALLOWED_HEADERS = new String[] {
            "Content-Type"
        };
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        Objects.requireNonNull(registry);

        registry.addMapping("/**")
                .allowedOrigins(ALLOWED_ORIGINS)
                .allowedMethods(ALLOWED_METHODS)
                .allowedHeaders(ALLOWED_HEADERS)
                .allowCredentials(false);
    }
}
