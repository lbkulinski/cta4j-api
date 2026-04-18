package com.cta4j.api.bus.controller;

import com.cta4j.api.bus.dto.Route;
import com.cta4j.api.bus.dto.Stop;
import com.cta4j.api.bus.repository.DirectionRepository;
import com.cta4j.api.bus.repository.RouteRepository;
import com.cta4j.api.bus.repository.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
public final class DirectoryController {
    private final RouteRepository routeRepository;

    private final DirectionRepository directionRepository;

    private final StopRepository stopRepository;

    @Autowired
    public DirectoryController(RouteRepository routeRepository, DirectionRepository directionRepository, StopRepository stopRepository) {
        this.routeRepository = routeRepository;

        this.directionRepository = directionRepository;

        this.stopRepository = stopRepository;
    }

    @GetMapping
    public List<Route> getRoutes() {
        return this.routeRepository.findAll();
    }

    @GetMapping("/{routeId}/directions")
    public List<String> getDirections(@PathVariable String routeId) {
        return this.directionRepository.getDirections(routeId);
    }

    @GetMapping("/{routeId}/directions/{direction}/stops")
    public List<Stop> getStops(@PathVariable String routeId, @PathVariable String direction) {
        return this.stopRepository.findAllByRouteIdAndDirection(routeId, direction);
    }
}
