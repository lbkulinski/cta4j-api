package app.cta4j.station.controller;

import app.cta4j.station.dto.StationArrivalDto;
import app.cta4j.station.dto.StationDto;
import app.cta4j.station.service.ArrivalService;
import app.cta4j.station.service.StationService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stations")
@RequiredArgsConstructor
public final class StationController {
    private final StationService stationService;

    private final ArrivalService arrivalService;

    @GetMapping
    public List<StationDto> getStations() {
        return this.stationService.getStations();
    }

    @GetMapping("/{stationId}/arrivals")
    public List<StationArrivalDto> getArrivals(@PathVariable @Positive int stationId) {
        return this.arrivalService.getArrivals(stationId);
    }
}
