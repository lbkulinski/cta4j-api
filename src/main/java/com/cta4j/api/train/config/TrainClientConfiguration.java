package com.cta4j.api.train.config;

import com.cta4j.api.aws.client.AwsSecretsClient;
import com.cta4j.train.client.TrainClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TrainClientConfiguration {
    @Bean
    public TrainClient buildTrainClient(AwsSecretsClient awsSecretsClient) {
        String apiKey = awsSecretsClient.getSecret()
                                        .cta()
                                        .trainApiKey();

        return TrainClient.builder()
                          .apiKey(apiKey)
                          .build();
    }
}
