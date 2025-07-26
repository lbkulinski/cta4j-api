package app.cta4j.train.controller;

import app.cta4j.train.dto.TrainArrivalDto;
import app.cta4j.train.dto.TrainStationDto;
import app.cta4j.train.service.TrainStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RequestMapping("/api/trains/stations")
@RestController
public final class TrainStationController {
    private final TrainStationService trainStationService;

    @Autowired
    public TrainStationController(TrainStationService trainStationService) {
        this.trainStationService = Objects.requireNonNull(trainStationService);
    }

    @GetMapping
    public List<TrainStationDto> getStations() {
        return this.trainStationService.getStations();
    }

    @GetMapping("/{stationId}/arrivals")
    public List<TrainArrivalDto> getArrivals(@PathVariable String stationId) {
        Objects.requireNonNull(stationId);

        return this.trainStationService.getArrivals(stationId);
    }
}
