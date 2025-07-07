package app.cta4j.controller;

import app.cta4j.model.Route;
import app.cta4j.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    public List<Route> getRoutes() {
        return this.routeService.getRoutes();
    }
}
