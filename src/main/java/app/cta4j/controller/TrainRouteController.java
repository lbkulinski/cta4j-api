package app.cta4j.controller;

import app.cta4j.dto.TrainLocationDto;
import app.cta4j.dto.TrainRoute;
import app.cta4j.service.TrainRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RequestMapping("/api/trains/routes")
@RestController
public final class TrainRouteController {
    private final TrainRouteService trainRouteService;

    @Autowired
    public TrainRouteController(TrainRouteService trainRouteService) {
        this.trainRouteService = Objects.requireNonNull(trainRouteService);
    }

    @GetMapping
    public List<TrainRoute> getRoutes() {
        return this.trainRouteService.getRoutes();
    }

    @GetMapping("/{route}/locations")
    public List<TrainLocationDto> getLocations(@PathVariable String route) {
        Objects.requireNonNull(route);

        return this.trainRouteService.getLocations(route);
    }
}
