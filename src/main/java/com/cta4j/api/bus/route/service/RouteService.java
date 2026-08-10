package com.cta4j.api.bus.route.service;

import com.cta4j.api.bus.route.exception.RouteNotFoundException;
import com.cta4j.api.bus.route.model.Route;
import com.cta4j.api.bus.route.model.RouteStop;
import com.cta4j.api.bus.route.repository.RouteDirectionsRepository;
import com.cta4j.api.bus.route.repository.RouteRepository;
import com.cta4j.api.bus.route.repository.RouteStopRepository;
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
        return this.routeRepository.findAll();
    }

    public List<String> getDirections(String routeId) {
        Objects.requireNonNull(routeId);

        List<String> directions = this.routeDirectionsRepository.findAllByRouteId(routeId);

        if (directions.isEmpty()) {
            throw new RouteNotFoundException(routeId);
        }

        return List.copyOf(directions);
    }

    public List<RouteStop> getStops(String routeId, String direction) {
        Objects.requireNonNull(routeId);
        Objects.requireNonNull(direction);

        List<RouteStop> stops = this.routeStopRepository.findAllByRouteIdAndDirection(routeId, direction);

        if (stops.isEmpty()) {
            throw new RouteNotFoundException(routeId, direction);
        }

        return List.copyOf(stops);
    }
}
