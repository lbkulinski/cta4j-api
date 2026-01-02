package app.cta4j.busroute.service;

import app.cta4j.busroute.dto.StopArrivalDto;
import app.cta4j.busroute.mapper.StopArrivalMapper;
import app.cta4j.busroute.repository.StopRepository;
import com.cta4j.bus.client.BusClient;
import com.cta4j.bus.model.StopArrival;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public final class StopArrivalService {
    private final StopRepository stopRepository;
    private final BusClient busClient;
    private final StopArrivalMapper stopArrivalMapper;

    @Autowired
    public StopArrivalService(
        StopRepository stopRepository,
        BusClient busClient,
        StopArrivalMapper stopArrivalMapper
    ) {
        this.stopRepository = stopRepository;
        this.busClient = busClient;
        this.stopArrivalMapper = stopArrivalMapper;
    }

    public List<StopArrivalDto> getArrivals(String route, String stopId) {
        if (route == null) {
            throw new IllegalArgumentException("route must not be null");
        }

        if (stopId == null) {
            throw new IllegalArgumentException("stopId must not be null");
        }

        if (!this.stopRepository.existsByRouteIdAndStopId(route, stopId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        List<StopArrival> arrivals = this.busClient.getStopArrivals(route, stopId);

        if (arrivals == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return arrivals.stream()
                       .map(this.stopArrivalMapper::toDomain)
                       .toList();
    }
}
