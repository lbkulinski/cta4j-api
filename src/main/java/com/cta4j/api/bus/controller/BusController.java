package com.cta4j.api.bus.controller;

import com.cta4j.api.bus.dto.BusResponse;
import com.cta4j.api.bus.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/buses")
public final class BusController {
    private final BusService busService;

    @Autowired
    public BusController(BusService busService) {
        this.busService = busService;
    }

    @GetMapping("/{id}")
    public BusResponse getBus(@PathVariable String id) {
        return this.busService.getBus(id);
    }
}
