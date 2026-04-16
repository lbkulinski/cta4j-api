package app.cta4j.bus.config;

import app.cta4j.aws.client.AwsSecretsClient;
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
