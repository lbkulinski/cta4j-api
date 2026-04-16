package app.cta4j.bus.controller;

import app.cta4j.bus.dto.Route;
import app.cta4j.bus.dto.Stop;
import app.cta4j.bus.repository.DirectionRepository;
import app.cta4j.bus.repository.RouteRepository;
import app.cta4j.bus.repository.StopRepository;
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
