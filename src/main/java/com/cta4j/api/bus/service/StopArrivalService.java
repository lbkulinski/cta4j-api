package com.cta4j.api.bus.service;

import com.cta4j.api.bus.dto.StopArrival;
import com.cta4j.api.bus.repository.RouteRepository;
import com.cta4j.api.bus.repository.RouteStopRepository;
import com.cta4j.bus.BusApi;
import com.cta4j.bus.prediction.model.Prediction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public final class StopArrivalService {
    private final RouteRepository routeRepository;
    private final RouteStopRepository routeStopRepository;
    private final BusApi busApi;

    @Autowired
    public StopArrivalService(
        RouteRepository routeRepository,
        RouteStopRepository routeStopRepository,
        BusApi busApi
    ) {
        this.routeRepository = routeRepository;
        this.routeStopRepository = routeStopRepository;
        this.busApi = busApi;
    }

    public List<StopArrival> getArrivals(String route, String stopId) {
        Objects.requireNonNull(route, "route must not be null");
        Objects.requireNonNull(stopId, "stopId must not be null");



        List<Prediction> predictions = this.busApi.predictions()
                                                  .findByRouteIdAndStopId(route, stopId);

//        List<com.cta4j.bus.model.StopArrival> arrivals = this.busClient.getStopArrivals(route, stopId);
//
//        if ((arrivals == null) || arrivals.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
//
//        return arrivals.stream()
//                       .map(this.stopArrivalMapper::toDomain)
//                       .toList();
    }
}
