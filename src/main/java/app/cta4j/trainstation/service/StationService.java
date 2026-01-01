package app.cta4j.trainstation.service;

import app.cta4j.trainstation.dto.StationDto;
import app.cta4j.trainstation.mapper.StationMapper;
import app.cta4j.trainstation.model.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;

@Service
public class StationService {
    private final DynamoDbTable<Station> stations;

    private final StationMapper stationMapper;

    @Autowired
    public StationService(Environment env, DynamoDbEnhancedClient dynamoDbClient, StationMapper stationMapper) {
        String stationsTableName = env.getRequiredProperty("app.aws.dynamodb.tables.stations");

        var stationsSchema = TableSchema.fromImmutableClass(Station.class);

        this.stations = dynamoDbClient.table(stationsTableName, stationsSchema);

        this.stationMapper = stationMapper;
    }

    @Cacheable("stations")
    public List<StationDto> getStations() {
        return this.stations.scan()
                            .items()
                            .stream()
                            .map(this.stationMapper::toDomain)
                            .toList();
    }
}
