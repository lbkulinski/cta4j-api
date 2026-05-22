package com.cta4j.api.bus.service;

import com.cta4j.api.bus.model.Route;
import com.cta4j.api.bus.model.RouteStop;
import com.cta4j.api.bus.repository.RouteDirectionsRepository;
import com.cta4j.api.bus.repository.RouteRepository;
import com.cta4j.api.bus.repository.RouteStopRepository;
import org.jspecify.annotations.NullMarked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@NullMarked
public final class RouteService {
    private final RouteRepository routeRepository;
    private final RouteDirectionsRepository routeDirectionsRepository;
    private final RouteStopRepository routeStopRepository;

    @Autowired
    public RouteService(
        RouteRepository routeRepository,
        RouteDirectionsRepository routeDirectionsRepository,
        RouteStopRepository routeStopRepository
    ) {
        this.routeRepository = routeRepository;
        this.routeDirectionsRepository = routeDirectionsRepository;
        this.routeStopRepository = routeStopRepository;
    }

    public List<Route> getRoutes() {
        return this.routeRepository.getAll();
    }

    public List<String> getDirections(String routeId) {
        Objects.requireNonNull(routeId);

        return this.routeDirectionsRepository.getAllByRouteId(routeId);
    }

    public List<RouteStop> getStops(String routeId, String direction) {
        Objects.requireNonNull(routeId);
        Objects.requireNonNull(direction);

        return this.routeStopRepository.getAllByRouteIdAndDirection(routeId, direction);
    }
}
