package com.cta4j.api.bus.stop.repository;

import com.cta4j.api.aws.config.DynamoDbProperties;
import com.cta4j.api.bus.stop.model.Stop;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.Objects;
import java.util.Optional;

@Repository
@NullMarked
public class StopRepository {
    private final DynamoDbTable<@Nullable Stop> stops;

    @Autowired
    public StopRepository(
        DynamoDbEnhancedClient dynamoDbClient,
        DynamoDbProperties tableProperties
    ) {
        TableSchema<Stop> schema = TableSchema.fromImmutableClass(Stop.class);

        this.stops = dynamoDbClient.table(tableProperties.stops(), schema);
    }

    @Cacheable("stopById")
    public Optional<Stop> getById(String id) {
        Objects.requireNonNull(id);

        Key key = Key.builder()
                     .partitionValue(id)
                     .build();

        Stop stop = this.stops.getItem(key);

        return Optional.ofNullable(stop);
    }
}
