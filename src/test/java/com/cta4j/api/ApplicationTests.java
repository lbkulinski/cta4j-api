package com.cta4j.api;

import com.cta4j.bus.BusApi;
import com.cta4j.train.TrainApi;
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
    BusApi busApi;

    @MockitoBean
    TrainApi trainApi;

    @Test
    void contextLoads() {
    }
}
