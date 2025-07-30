package app.cta4j.bus.config;

import app.cta4j.bus.client.BusApiClient;
import app.cta4j.service.SecretService;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Objects;

@Configuration
public class BusApiClientConfiguration {
    @Bean
    public BusApiClient buildBusApiClient(Environment env, SecretService secretService, ObjectMapper objectMapper) {
        Objects.requireNonNull(env);

        Objects.requireNonNull(secretService);

        String baseUrl = env.getRequiredProperty("app.cta.api.buses.base-url");

        String apiKey = secretService.getSecret()
                                     .cta()
                                     .busApiKey();

        JacksonDecoder decoder = new JacksonDecoder(objectMapper);

        return Feign.builder()
                    .requestInterceptor(template -> {
                        template.query("key", apiKey);

                        template.query("format", "json");
                    })
                    .decoder(decoder)
                    .target(BusApiClient.class, baseUrl);
    }
}
