package app.cta4j.common.config;

import app.cta4j.secretsmanager.service.SecretService;
import com.cta4j.bus.client.BusClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BusClientConfiguration {
    @Bean
    public BusClient buildBusClient(SecretService secretService) {
        String apiKey = secretService.getSecret()
                                     .cta()
                                     .busApiKey();

        return BusClient.builder()
                        .apiKey(apiKey)
                        .build();
    }
}
