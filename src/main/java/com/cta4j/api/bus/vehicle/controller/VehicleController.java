package com.cta4j.api.bus.vehicle.controller;

import com.cta4j.api.bus.common.dto.ArrivalDto;
import com.cta4j.api.bus.common.dto.ArrivalsDto;
import com.cta4j.api.bus.common.mapper.ArrivalMapper;
import com.cta4j.api.bus.vehicle.dto.VehicleDto;
import com.cta4j.api.bus.vehicle.mapper.VehicleMapper;
import com.cta4j.api.bus.vehicle.model.Vehicle;
import com.cta4j.api.bus.vehicle.service.VehicleService;
import org.jspecify.annotations.NullMarked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bus/vehicles")
@NullMarked
public final class VehicleController {
    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/{vehicleId}")
    public VehicleDto getVehicle(@PathVariable String vehicleId) {
        Vehicle vehicle = this.vehicleService.getVehicle(vehicleId);

        return VehicleMapper.INSTANCE.toDto(vehicle);
    }

    @GetMapping("/{vehicleId}/arrivals")
    public ArrivalsDto getArrivals(@PathVariable String vehicleId) {
        List<ArrivalDto> arrivals = this.vehicleService.getArrivals(vehicleId)
                                                       .stream()
                                                       .map(ArrivalMapper.INSTANCE::toDto)
                                                       .toList();

        return new ArrivalsDto(arrivals);
    }
}
