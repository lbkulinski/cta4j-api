package com.cta4j.api.bus.controller;

import com.cta4j.api.bus.dto.Route;
import com.cta4j.api.bus.dto.Stop;
import com.cta4j.api.bus.repository.RouteDirectionsRepository;
import com.cta4j.api.bus.repository.RouteRepository;
import com.cta4j.api.bus.repository.RouteStopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bus/routes")
public final class RouteController {
    private final RouteRepository routeRepository;

    private final RouteDirectionsRepository routeDirectionsRepository;

    private final RouteStopRepository routeStopRepository;

    @Autowired
    public RouteController(RouteRepository routeRepository, RouteDirectionsRepository routeDirectionsRepository, RouteStopRepository routeStopRepository) {
        this.routeRepository = routeRepository;

        this.routeDirectionsRepository = routeDirectionsRepository;

        this.routeStopRepository = routeStopRepository;
    }

    @GetMapping
    public List<Route> getRoutes() {
        return this.routeRepository.findAll();
    }

    @GetMapping("/{routeId}/directions")
    public List<String> getDirections(@PathVariable String routeId) {
        return this.routeDirectionsRepository.getDirections(routeId);
    }

    @GetMapping("/{routeId}/directions/{direction}/stops")
    public List<Stop> getStops(@PathVariable String routeId, @PathVariable String direction) {
        return this.routeStopRepository.findAllByRouteAndDirection(routeId, direction);
    }
}
