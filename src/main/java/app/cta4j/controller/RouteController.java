package app.cta4j.controller;

import app.cta4j.dto.RouteDto;
import app.cta4j.dto.StopDto;
import app.cta4j.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RequestMapping("/api/routes")
@RestController
public final class RouteController {
    private final RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = Objects.requireNonNull(routeService);
    }

    @GetMapping
    public List<RouteDto> getRoutes() {
        return this.routeService.getRoutes();
    }

    @GetMapping("/{routeId}/directions")
    public List<String> getDirections(@PathVariable String routeId) {
        Objects.requireNonNull(routeId);

        return this.routeService.getDirections(routeId);
    }

    @GetMapping("/{routeId}/directions/{direction}/stops")
    public List<StopDto> getStops(@PathVariable String routeId, @PathVariable String direction) {
        Objects.requireNonNull(routeId);

        Objects.requireNonNull(direction);

        return this.routeService.getStops(routeId, direction);
    }
}
