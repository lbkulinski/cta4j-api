package com.cta4j.api.bus.service;

import com.cta4j.api.bus.dto.StopArrival;
import com.cta4j.api.bus.repository.RouteRepository;
import com.cta4j.api.bus.repository.StopRepository;
import com.cta4j.bus.BusApi;
import com.cta4j.bus.prediction.model.Prediction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
public final class StopArrivalService {
    private final RouteRepository routeRepository;
    private final StopRepository stopRepository;
    private final BusApi busApi;

    @Autowired
    public StopArrivalService(
        RouteRepository routeRepository,
        StopRepository stopRepository,
        BusApi busApi
    ) {
        this.routeRepository = routeRepository;
        this.stopRepository = stopRepository;
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
