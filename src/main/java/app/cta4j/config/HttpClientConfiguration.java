package app.cta4j.config;

import app.cta4j.client.StationArrivalClient;
import app.cta4j.client.TrainClient;
import app.cta4j.service.SecretService;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Objects;

@Configuration
public class HttpClientConfiguration {
    @Bean
    public StationArrivalClient buildStationArrivalClient(Environment env, SecretService secretService,
        ObjectMapper objectMapper) {
        Objects.requireNonNull(env);

        Objects.requireNonNull(secretService);

        String baseUrl = env.getRequiredProperty("app.cta.api.trains.base-url");

        String apiKey = secretService.getSecret()
                                     .cta()
                                     .trainApiKey();

        JacksonDecoder decoder = new JacksonDecoder(objectMapper);

        return Feign.builder()
                    .requestInterceptor(template -> {
                        template.query("key", apiKey);

                        template.query("outputType", "JSON");
                    })
                    .decoder(decoder)
                    .target(StationArrivalClient.class, baseUrl);
    }

    @Bean
    public TrainClient buildTrainClient(Environment env, SecretService secretService, ObjectMapper objectMapper) {
        Objects.requireNonNull(env);

        Objects.requireNonNull(secretService);

        String baseUrl = env.getRequiredProperty("app.cta.api.trains.base-url");

        String apiKey = secretService.getSecret()
                                     .cta()
                                     .trainApiKey();

        JacksonDecoder decoder = new JacksonDecoder(objectMapper);

        return Feign.builder()
                    .requestInterceptor(template -> {
                        template.query("key", apiKey);

                        template.query("outputType", "JSON");
                    })
                    .decoder(decoder)
                    .target(TrainClient.class, baseUrl);
    }
}
