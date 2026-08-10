package com.cta4j.api.common.config;

import com.cta4j.api.aws.client.SecretsClient;
import com.rollbar.notifier.Rollbar;
import com.rollbar.notifier.config.Config;
import com.rollbar.notifier.config.ConfigBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class RollbarConfig {
    @Bean
    public Rollbar rollbar(Environment env, SecretsClient secretsClient) {
        String accessToken = secretsClient.getSecret()
                                          .rollbar()
                                          .accessToken();

        String environment = env.getRequiredProperty("app.rollbar.environment");

        Config config = ConfigBuilder.withAccessToken(accessToken)
                                     .environment(environment)
                                     .build();

        return Rollbar.init(config);
    }
}
