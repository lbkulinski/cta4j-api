package com.cta4j.api.common.config;

import com.cta4j.api.aws.client.AwsSecretsClient;
import com.rollbar.notifier.Rollbar;
import com.rollbar.notifier.config.Config;
import com.rollbar.notifier.config.ConfigBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class RollbarConfiguration {
    @Bean
    public Rollbar buildRollbar(Environment env, AwsSecretsClient awsSecretsClient) {
        String accessToken = awsSecretsClient.getSecret()
                                             .rollbar()
                                             .accessToken();

        String environment = env.getRequiredProperty("app.rollbar.environment");

        Config config = ConfigBuilder.withAccessToken(accessToken)
                                     .environment(environment)
                                     .build();

        return Rollbar.init(config);
    }
}
