package app.cta4j.config;

import app.cta4j.api.CtaTrainApi;
import app.cta4j.service.SecretService;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class CtaApiConfiguration {
    @Bean
    public CtaTrainApi buildCtaTrainApi(Environment env, SecretService secretService, ObjectMapper objectMapper) {
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
                    .target(CtaTrainApi.class, baseUrl);
    }
}
