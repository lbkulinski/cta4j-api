package com.cta4j.api.bus.common.config;

import com.cta4j.api.aws.client.SecretsClient;
import com.cta4j.bus.BusApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BusApiConfig {
    @Bean
    public BusApi busApi(SecretsClient secretsClient) {
        String apiKey = secretsClient.getSecret()
                                     .cta()
                                     .busApiKey();

        return BusApi.builder(apiKey)
                     .build();
    }
}
