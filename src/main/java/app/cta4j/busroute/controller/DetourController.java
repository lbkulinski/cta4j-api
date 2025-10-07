package app.cta4j.busroute.controller;

import app.cta4j.busroute.dto.Detour;
import app.cta4j.busroute.service.DetourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/routes/{routeId}/directions/{direction}/detours")
public final class DetourController {
    private final DetourService detourService;

    @Autowired
    public DetourController(DetourService detourService) {
        this.detourService = detourService;
    }

    @GetMapping
    public List<Detour> getArrivals(@PathVariable String routeId, @PathVariable String direction) {
        return this.detourService.getDetours(routeId, direction);
    }
}
