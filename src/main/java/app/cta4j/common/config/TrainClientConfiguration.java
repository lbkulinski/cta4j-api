package app.cta4j.common.config;

import app.cta4j.secretsmanager.service.SecretService;
import com.cta4j.train.client.TrainClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TrainClientConfiguration {
    @Bean
    public TrainClient buildTrainClient(SecretService secretService) {
        String apiKey = secretService.getSecret()
                                     .cta()
                                     .trainApiKey();

        return TrainClient.builder()
                          .apiKey(apiKey)
                          .build();
    }
}
