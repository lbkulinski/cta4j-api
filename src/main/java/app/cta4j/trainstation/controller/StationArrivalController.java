package app.cta4j.trainstation.controller;

import app.cta4j.trainstation.dto.StationArrival;
import app.cta4j.trainstation.service.StationArrivalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stations/{stationId}/arrivals")
public final class StationArrivalController {
    private final StationArrivalService stationArrivalService;

    @Autowired
    public StationArrivalController(StationArrivalService stationArrivalService) {
        this.stationArrivalService = stationArrivalService;
    }

    @GetMapping
    public List<StationArrival> getStationArrivals(@PathVariable String stationId) {
        return this.stationArrivalService.getArrivals(stationId);
    }
}
