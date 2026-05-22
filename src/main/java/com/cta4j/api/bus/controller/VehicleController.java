package com.cta4j.api.bus.controller;

import com.cta4j.api.bus.dto.VehicleDto;
import com.cta4j.api.bus.mapper.VehicleMapper;
import com.cta4j.api.bus.model.Vehicle;
import com.cta4j.api.bus.response.VehicleResponse;
import com.cta4j.api.bus.service.VehicleService;
import org.jspecify.annotations.NullMarked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public VehicleResponse getVehicle(@PathVariable String vehicleId) {
        Vehicle vehicle = this.vehicleService.getVehicle(vehicleId);

        VehicleDto vehicleDto = VehicleMapper.INSTANCE.toDto(vehicle);

        return new VehicleResponse(vehicleDto);
    }
}
