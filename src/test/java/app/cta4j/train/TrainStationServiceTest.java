package app.cta4j.train;

import app.cta4j.train.client.TrainApiClient;
import app.cta4j.train.model.TrainStation;
import app.cta4j.train.service.TrainStationService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;

@ExtendWith(MockitoExtension.class)
public class TrainStationServiceTest {
    @Mock
    Environment env;

    @Mock
    DynamoDbEnhancedClient dynamoDbClient;

    @Mock
    TrainApiClient trainApiClient;

    @Mock
    DynamoDbTable<TrainStation> stations;

    @InjectMocks
    TrainStationService trainStationService;
}
