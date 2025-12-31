package app.cta4j.common.config;

import app.cta4j.aws.client.AwsSecretsClient;
import com.cta4j.train.client.TrainClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TrainClientConfig {
    private final AwsSecretsClient awsSecretsClient;

    @Autowired
    public TrainClientConfig(AwsSecretsClient awsSecretsClient) {
        this.awsSecretsClient = awsSecretsClient;
    }

    @Bean
    public TrainClient trainClient() {
        String apiKey = this.awsSecretsClient.getAppSecret()
                                             .cta()
                                             .trainApiKey();

        return TrainClient.builder()
                          .apiKey(apiKey)
                          .build();
    }
}
