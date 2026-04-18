package com.cta4j.api.bus.config;

import com.cta4j.api.aws.client.AwsSecretsClient;
import com.cta4j.bus.BusApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BusApiConfig {
    @Bean
    public BusApi busApi(AwsSecretsClient awsSecretsClient) {
        String apiKey = awsSecretsClient.getSecret()
                                        .cta()
                                        .busApiKey();

        return BusApi.builder(apiKey)
                     .build();
    }
}
