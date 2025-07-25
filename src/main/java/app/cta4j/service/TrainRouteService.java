package app.cta4j.service;

import app.cta4j.client.TrainClient;
import app.cta4j.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
public final class TrainRouteService {
    private final TrainClient trainClient;

    @Autowired
    public TrainRouteService(TrainClient trainClient) {
        this.trainClient = Objects.requireNonNull(trainClient);
    }

    public List<LocationTrainDto> getTrainLocations(String route) {
        Objects.requireNonNull(route);

        LocationResponseDto response = this.trainClient.getTrainLocations(route);

        if (response == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        LocationBodyDto body = response.body();

        if (body == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        List<LocationRouteDto> routes = body.routes();

        if ((routes == null) || routes.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        List<LocationTrainDto> trains = routes.getFirst()
                                              .trains();

        return List.copyOf(trains);
    }
}
