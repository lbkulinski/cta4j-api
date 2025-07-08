package app.cta4j.controller;

import app.cta4j.dto.StationDto;
import app.cta4j.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RequestMapping("/api/stations")
@RestController
public final class StationController {
    private final StationService stationService;

    @Autowired
    public StationController(StationService stationService) {
        this.stationService = Objects.requireNonNull(stationService);
    }

    @GetMapping
    public List<StationDto> getStations() {
        return this.stationService.getStations();
    }
}
