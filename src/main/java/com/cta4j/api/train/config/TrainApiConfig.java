package com.cta4j.api.train.config;

import com.cta4j.api.aws.client.SecretsClient;
import com.cta4j.train.TrainApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TrainApiConfig {
    @Bean
    public TrainApi buildTrainClient(SecretsClient secretsClient) {
        String apiKey = secretsClient.getSecret()
                                     .cta()
                                     .trainApiKey();

        return TrainApi.builder(apiKey)
                       .build();
    }
}
