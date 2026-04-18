package com.cta4j.api.train.controller;

import com.cta4j.api.train.dto.StationArrival;
import com.cta4j.api.train.service.StationArrivalService;
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
