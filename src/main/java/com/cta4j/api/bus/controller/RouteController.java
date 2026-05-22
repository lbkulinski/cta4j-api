package com.cta4j.api.bus.controller;

import com.cta4j.api.bus.response.RouteDirectionsResponse;
import com.cta4j.api.bus.dto.RouteDto;
import com.cta4j.api.bus.dto.RouteStopDto;
import com.cta4j.api.bus.response.RouteStopsResponse;
import com.cta4j.api.bus.response.RoutesResponse;
import com.cta4j.api.bus.mapper.RouteMapper;
import com.cta4j.api.bus.mapper.RouteStopMapper;
import com.cta4j.api.bus.service.RouteService;
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
    public RoutesResponse getRoutes() {
        List<RouteDto> routes = this.routeService.getRoutes()
                                                 .stream()
                                                 .map(RouteMapper.INSTANCE::toDto)
                                                 .toList();

        return new RoutesResponse(routes);
    }

    @GetMapping("/{routeId}/directions")
    public RouteDirectionsResponse getDirections(@PathVariable String routeId) {
        List<String> directions = this.routeService.getDirections(routeId);

        return new RouteDirectionsResponse(directions);
    }

    @GetMapping("/{routeId}/directions/{direction}/stops")
    public RouteStopsResponse getStops(
        @PathVariable String routeId,
        @PathVariable String direction
    ) {
        List<RouteStopDto> stops = this.routeService.getStops(routeId, direction)
                                                    .stream()
                                                    .map(RouteStopMapper.INSTANCE::toDto)
                                                    .toList();

        return new RouteStopsResponse(stops);
    }
}
