package app.cta4j.trainstation.service;

import app.cta4j.trainstation.dto.StationArrivalDto;
import app.cta4j.trainstation.mapper.StationArrivalMapper;
import app.cta4j.trainstation.repository.StationRepository;
import com.cta4j.train.client.TrainClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public final class StationArrivalService {
    private final StationRepository stationRepository;
    private final TrainClient trainClient;
    private final StationArrivalMapper stationArrivalMapper;

    @Autowired
    public StationArrivalService(
        StationRepository stationRepository,
        TrainClient trainClient,
        StationArrivalMapper stationArrivalMapper
    ) {
        this.stationRepository = stationRepository;
        this.trainClient = trainClient;
        this.stationArrivalMapper = stationArrivalMapper;
    }

    public List<StationArrivalDto> getArrivals(String stationId) {
        if (stationId == null) {
            throw new IllegalArgumentException("stationId must not be null");
        }

        if (!this.stationRepository.existsById(stationId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return this.trainClient.getStationArrivals(stationId)
                               .stream()
                               .map(this.stationArrivalMapper::toDomain)
                               .toList();
    }
}
