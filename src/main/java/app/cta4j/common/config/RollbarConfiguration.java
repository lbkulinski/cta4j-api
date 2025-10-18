package app.cta4j.common.config;

import app.cta4j.secretsmanager.service.SecretService;
import com.rollbar.notifier.Rollbar;
import com.rollbar.notifier.config.Config;
import com.rollbar.notifier.config.ConfigBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class RollbarConfiguration {
    @Bean
    public Rollbar buildRollbar(Environment env, SecretService secretService){
        String accessToken = secretService.getSecret()
                                          .rollbar()
                                          .accessToken();

        String environment = env.getRequiredProperty("app.rollbar.environment");

        Config config = ConfigBuilder.withAccessToken(accessToken)
                                     .environment(environment)
                                     .build();

        return Rollbar.init(config);
    }
}
