package app.cta4j.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class DynamoDbClientConfiguration {
    @Bean
    public DynamoDbEnhancedClient buildDynamoDbClient() {
        DynamoDbClient dynamoDbClient = DynamoDbClient.builder()
                                                      .region(Region.US_EAST_2)
                                                      .build();

        return DynamoDbEnhancedClient.builder()
                                     .dynamoDbClient(dynamoDbClient)
                                     .build();
    }
}
