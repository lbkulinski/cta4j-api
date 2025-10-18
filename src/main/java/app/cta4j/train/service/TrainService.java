package app.cta4j.train.service;

import app.cta4j.train.dto.TrainCoordinates;
import app.cta4j.train.dto.Train;
import app.cta4j.train.dto.UpcomingTrainArrival;
import app.cta4j.train.mapper.TrainMapper;
import com.cta4j.client.TrainClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
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

    public Train getLocation(String run) {
        Objects.requireNonNull(run);

        Optional<com.cta4j.model.train.Train> optionalTrain = this.trainClient.getTrain(run);

        if (optionalTrain.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        com.cta4j.model.train.Train train = optionalTrain.get();

        com.cta4j.model.train.TrainCoordinates ctaPosition = train.coordinates();

        TrainCoordinates coordinates = this.trainMapper.toDomainCoordinates(ctaPosition);

        List<com.cta4j.model.train.UpcomingTrainArrival> ctaArrivals = train.arrivals();

        List<UpcomingTrainArrival> arrivals = ctaArrivals.stream()
                                                         .map(this.trainMapper::toDomainArrival)
                                                         .toList();

        return new Train(coordinates, arrivals);
    }
}
