package com.cta4j.api.bus.route.controller;

import com.cta4j.api.bus.route.dto.RouteDirectionsDto;
import com.cta4j.api.bus.route.dto.RouteStopDto;
import com.cta4j.api.bus.route.dto.RouteStopsDto;
import com.cta4j.api.bus.route.mapper.RouteStopMapper;
import com.cta4j.api.bus.route.dto.RouteDto;
import com.cta4j.api.bus.route.mapper.RouteMapper;
import com.cta4j.api.bus.route.service.RouteService;
import com.cta4j.api.bus.route.dto.RoutesDto;
import org.jspecify.annotations.NullMarked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bus/routes")
@NullMarked
public final class RouteController {
    private final RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping
    public RoutesDto getRoutes() {
        List<RouteDto> routes = this.routeService.getRoutes()
                                                 .stream()
                                                 .map(RouteMapper.INSTANCE::toDto)
                                                 .toList();

        return new RoutesDto(routes);
    }

    @GetMapping("/{routeId}/directions")
    public RouteDirectionsDto getDirections(@PathVariable String routeId) {
        List<String> directions = this.routeService.getDirections(routeId);

        return new RouteDirectionsDto(directions);
    }

    @GetMapping("/{routeId}/directions/{direction}/stops")
    public RouteStopsDto getStops(
        @PathVariable String routeId,
        @PathVariable String direction
    ) {
        List<RouteStopDto> stops = this.routeService.getStops(routeId, direction)
                                                    .stream()
                                                    .map(RouteStopMapper.INSTANCE::toDto)
                                                    .toList();

        return new RouteStopsDto(stops);
    }
}
