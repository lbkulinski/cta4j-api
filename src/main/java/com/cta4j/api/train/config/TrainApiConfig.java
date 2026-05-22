package com.cta4j.api.train.config;

import com.cta4j.api.aws.client.AwsSecretsClient;
import com.cta4j.train.TrainApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TrainApiConfig {
    @Bean
    public TrainApi buildTrainClient(AwsSecretsClient awsSecretsClient) {
        String apiKey = awsSecretsClient.getSecret()
                                        .cta()
                                        .trainApiKey();

        return TrainApi.builder(apiKey)
                       .build();
    }
}
