package app.cta4j.busroute.controller;

import app.cta4j.busroute.dto.Route;
import app.cta4j.busroute.dto.Stop;
import app.cta4j.busroute.service.DirectionService;
import app.cta4j.busroute.service.RouteService;
import app.cta4j.busroute.service.StopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
public final class DirectoryController {
    private final RouteService routeService;

    private final DirectionService directionService;

    private final StopService stopService;

    @Autowired
    public DirectoryController(RouteService routeService, DirectionService directionService, StopService stopService) {
        this.routeService = routeService;

        this.directionService = directionService;

        this.stopService = stopService;
    }

    @GetMapping
    public List<Route> getRoutes() {
        return this.routeService.getRoutes();
    }

    @GetMapping("/{routeId}/directions")
    public List<String> getDirections(@PathVariable String routeId) {
        return this.directionService.getDirections(routeId);
    }

    @GetMapping("/{routeId}/directions/{direction}/stops")
    public List<Stop> getStops(@PathVariable String routeId, @PathVariable String direction) {
        return this.stopService.getStops(routeId, direction);
    }
}
