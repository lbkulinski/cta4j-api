package app.cta4j;

import app.cta4j.bus.client.BusApiClient;
import app.cta4j.api.CtaTrainApi;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;

@SpringBootTest
class ApplicationTests {
    @MockitoBean
    SecretsManagerClient secretsManagerClient;

    @MockitoBean
    DynamoDbEnhancedClient dynamoDbClient;

    @MockitoBean
    BusApiClient busApiClient;

    @MockitoBean
    CtaTrainApi ctaTrainApi;

    @Test
    void contextLoads() {
    }
}
