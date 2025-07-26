package app.cta4j.train.service;

import app.cta4j.train.dto.*;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TrainRouteService {
    @Cacheable("trainRoutes")
    public List<TrainRoute> getRoutes() {
        TrainRoute[] routes = TrainRoute.values();

        return Arrays.stream(routes)
                     .filter(route -> route != TrainRoute.N_A)
                     .toList();
    }
}
