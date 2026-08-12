package com.cta4j.api.common.config;

import com.cta4j.api.aws.client.SecretsClient;
import com.rollbar.notifier.Rollbar;
import com.rollbar.notifier.config.Config;
import com.rollbar.notifier.config.ConfigBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RollbarConfig {
    @Bean
    public Rollbar rollbar(
        SecretsClient secretsClient,
        @Value("${app.rollbar.environment}") String environment
    ) {
        String accessToken = secretsClient.getSecret()
                                          .rollbar()
                                          .accessToken();

        Config config = ConfigBuilder.withAccessToken(accessToken)
                                     .environment(environment)
                                     .build();

        return Rollbar.init(config);
    }
}
