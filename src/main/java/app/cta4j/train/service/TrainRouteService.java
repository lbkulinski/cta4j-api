package app.cta4j.train.service;

import app.cta4j.train.client.TrainApiClient;
import app.cta4j.train.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class TrainRouteService {
    private final TrainApiClient trainApiClient;

    @Autowired
    public TrainRouteService(TrainApiClient trainApiClient) {
        this.trainApiClient = Objects.requireNonNull(trainApiClient);
    }

    @Cacheable("trainRoutes")
    public List<TrainRoute> getRoutes() {
        TrainRoute[] routes = TrainRoute.values();

        return Arrays.stream(routes)
                     .filter(route -> route != TrainRoute.N_A)
                     .toList();
    }

    public List<TrainLocationDto> getLocations(String route) {
        Objects.requireNonNull(route);

        TrainLocationResponseDto response = this.trainApiClient.getTrainLocations(route);

        if (response == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        TrainLocationBodyDto body = response.body();

        if (body == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        List<TrainLocationRouteDto> routes = body.routes();

        if ((routes == null) || routes.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        List<TrainLocationDto> trains = routes.getFirst()
                                              .trains();

        return List.copyOf(trains);
    }
}
