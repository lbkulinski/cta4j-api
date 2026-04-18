package com.cta4j.api.bus.controller;

import com.cta4j.api.bus.dto.StopArrival;
import com.cta4j.api.bus.service.StopArrivalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/routes/{routeId}/stops/{stopId}/arrivals")
public final class StopArrivalController {
    private final StopArrivalService stopArrivalService;

    @Autowired
    public StopArrivalController(StopArrivalService stopArrivalService) {
        this.stopArrivalService = stopArrivalService;
    }

    @GetMapping
    public List<StopArrival> getStopArrivals(@PathVariable String routeId, @PathVariable String stopId) {
        return this.stopArrivalService.getArrivals(routeId, stopId);
    }
}
