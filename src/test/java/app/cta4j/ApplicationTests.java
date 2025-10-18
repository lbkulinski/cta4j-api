package app.cta4j;

import com.cta4j.bus.client.BusClient;
import com.cta4j.train.client.TrainClient;
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
    BusClient busClient;

    @MockitoBean
    TrainClient trainClient;

    @Test
    void contextLoads() {
    }
}
