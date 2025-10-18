package app.cta4j.trainstation.service;

import app.cta4j.trainstation.dto.StationArrival;
import app.cta4j.trainstation.mapper.StationArrivalMapper;
import com.cta4j.train.client.TrainClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
public final class StationArrivalService {
    private final TrainClient trainClient;

    private final StationArrivalMapper stationArrivalMapper;

    @Autowired
    public StationArrivalService(TrainClient trainClient, StationArrivalMapper stationArrivalMapper) {
        this.trainClient = trainClient;

        this.stationArrivalMapper = stationArrivalMapper;
    }

    public List<StationArrival> getArrivals(String stationId) {
        Objects.requireNonNull(stationId);

        List<com.cta4j.train.model.StationArrival> arrivals = this.trainClient.getStationArrivals(stationId);

        if ((arrivals == null) || arrivals.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return arrivals.stream()
                       .map(this.stationArrivalMapper::toDomain)
                       .toList();
    }
}
