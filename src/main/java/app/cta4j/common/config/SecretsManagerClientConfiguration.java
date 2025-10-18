package app.cta4j.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;

@Configuration
public class SecretsManagerClientConfiguration {
    @Bean
    public SecretsManagerClient buildSecretsManagerClient() {
        return SecretsManagerClient.builder()
                                   .region(Region.US_EAST_2)
                                   .build();
    }
}
