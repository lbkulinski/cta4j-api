package com.cta4j.api.bus.service;

import com.cta4j.api.bus.exception.VehicleNotFoundException;
import com.cta4j.api.bus.mapper.VehicleMapper;
import com.cta4j.api.bus.model.Vehicle;
import com.cta4j.bus.BusApi;
import org.jspecify.annotations.NullMarked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@NullMarked
public final class VehicleService {
    private final BusApi busApi;

    @Autowired
    public VehicleService(BusApi busApi) {
        this.busApi = busApi;
    }

    public Vehicle getVehicle(String vehicleId) {
        Objects.requireNonNull(vehicleId);

        //fix not found case in cta4j SDK
        return this.busApi.vehicles()
                          .findById(vehicleId)
                          .map(VehicleMapper.INSTANCE::toModel)
                          .orElseThrow(() -> new VehicleNotFoundException(vehicleId));
    }
}
