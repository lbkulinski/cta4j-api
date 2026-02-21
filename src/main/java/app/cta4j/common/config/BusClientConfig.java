package app.cta4j.common.config;

import app.cta4j.aws.client.AwsSecretsClient;
import com.cta4j.bus.client.BusClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BusClientConfig {
    private final AwsSecretsClient awsSecretsClient;

    @Autowired
    public BusClientConfig(AwsSecretsClient awsSecretsClient) {
        this.awsSecretsClient = awsSecretsClient;
    }

    @Bean
    public BusClient busClient() {
        String apiKey = this.awsSecretsClient.getAppSecret()
                                             .cta()
                                             .busApiKey();

        return BusClient.builder()
                        .apiKey(apiKey)
                        .build();
    }
}
