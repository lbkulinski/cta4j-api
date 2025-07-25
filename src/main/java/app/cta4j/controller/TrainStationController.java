package app.cta4j.controller;

import app.cta4j.dto.StationArrivalDto;
import app.cta4j.dto.StationDto;
import app.cta4j.service.TrainStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RequestMapping("/api/train/stations")
@RestController
public final class TrainStationController {
    private final TrainStationService trainStationService;

    @Autowired
    public TrainStationController(TrainStationService trainStationService) {
        this.trainStationService = Objects.requireNonNull(trainStationService);
    }

    @GetMapping
    public List<StationDto> getStations() {
        return this.trainStationService.getStations();
    }

    @GetMapping("/{stationId}/arrivals")
    public List<StationArrivalDto> getArrivals(@PathVariable String stationId) {
        Objects.requireNonNull(stationId);

        return this.trainStationService.getArrivals(stationId);
    }
}
