package com.cta4j.api.train.service;

import com.cta4j.api.train.dto.Train;
import com.cta4j.api.train.mapper.TrainMapper;
import com.cta4j.train.client.TrainClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Optional;

@Service
public final class TrainService {
    private final TrainClient trainClient;

    private final TrainMapper trainMapper;

    @Autowired
    public TrainService(TrainClient trainClient, TrainMapper trainMapper) {
        this.trainClient = trainClient;

        this.trainMapper = trainMapper;
    }

    public Train getTrain(String run) {
        Objects.requireNonNull(run);

        Optional<com.cta4j.train.model.Train> optionalTrain = this.trainClient.getTrain(run);

        if (optionalTrain.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        com.cta4j.train.model.Train train = optionalTrain.get();

        return this.trainMapper.toDomain(train);
    }
}
