package app.cta4j.busroute.controller;

import app.cta4j.busroute.dto.RouteDto;
import app.cta4j.busroute.dto.StopDto;
import app.cta4j.busroute.service.DirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
public final class DirectoryController {
    private final DirectoryService directoryService;

    @Autowired
    public DirectoryController(DirectoryService directoryService) {
        this.directoryService = directoryService;
    }

    @GetMapping
    public List<RouteDto> getRoutes() {
        return this.directoryService.getRoutes();
    }

    @GetMapping("/{routeId}/directions")
    public List<String> getDirections(@PathVariable String routeId) {
        return this.directoryService.getDirections(routeId);
    }

    @GetMapping("/{routeId}/directions/{direction}/stops")
    public List<StopDto> getStops(@PathVariable String routeId, @PathVariable String direction) {
        return this.directoryService.getStops(routeId, direction);
    }
}
