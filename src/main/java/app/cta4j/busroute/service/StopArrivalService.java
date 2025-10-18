package app.cta4j.busroute.service;

import app.cta4j.busroute.dto.StopArrival;
import app.cta4j.busroute.mapper.StopArrivalMapper;
import com.cta4j.bus.client.BusClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
public final class StopArrivalService {
    private final BusClient busClient;

    private final StopArrivalMapper stopArrivalMapper;

    @Autowired
    public StopArrivalService(BusClient busClient, StopArrivalMapper stopArrivalMapper) {
        this.busClient = busClient;

        this.stopArrivalMapper = stopArrivalMapper;
    }

    public List<StopArrival> getArrivals(String route, String stopId) {
        Objects.requireNonNull(route);

        Objects.requireNonNull(stopId);

        List<com.cta4j.bus.model.StopArrival> arrivals = this.busClient.getStopArrivals(route, stopId);

        if ((arrivals == null) || arrivals.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return arrivals.stream()
                       .map(this.stopArrivalMapper::toDomain)
                       .toList();
    }
}
