package app.cta4j.bus.controller;

import app.cta4j.bus.dto.BusArrivalDto;
import app.cta4j.bus.dto.BusRouteDto;
import app.cta4j.bus.dto.BusStopDto;
import app.cta4j.bus.service.BusRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RequestMapping("/api/buses/routes")
@RestController
public final class BusRouteController {
    private final BusRouteService busRouteService;

    @Autowired
    public BusRouteController(BusRouteService busRouteService) {
        this.busRouteService = Objects.requireNonNull(busRouteService);
    }

    @GetMapping
    public List<BusRouteDto> getRoutes() {
        return this.busRouteService.getRoutes();
    }

    @GetMapping("/{routeId}/directions")
    public List<String> getDirections(@PathVariable String routeId) {
        Objects.requireNonNull(routeId);

        return this.busRouteService.getDirections(routeId);
    }

    @GetMapping("/{routeId}/directions/{direction}/stops")
    public List<BusStopDto> getStops(@PathVariable String routeId, @PathVariable String direction) {
        Objects.requireNonNull(routeId);

        Objects.requireNonNull(direction);

        return this.busRouteService.getStops(routeId, direction);
    }

    @GetMapping("/{routeId}/stops/{stopId}/arrivals")
    public List<BusArrivalDto> getArrivals(@PathVariable String routeId, @PathVariable String stopId) {
        Objects.requireNonNull(routeId);

        Objects.requireNonNull(stopId);

        return this.busRouteService.getArrivals(routeId, stopId);
    }
}
