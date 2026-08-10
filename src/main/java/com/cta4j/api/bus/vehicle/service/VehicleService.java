package com.cta4j.api.bus.vehicle.service;

import com.cta4j.api.bus.common.mapper.ArrivalMapper;
import com.cta4j.api.bus.common.model.Arrival;
import com.cta4j.api.bus.vehicle.exception.VehicleNotFoundException;
import com.cta4j.api.bus.vehicle.mapper.VehicleMapper;
import com.cta4j.api.bus.vehicle.model.Vehicle;
import com.cta4j.bus.BusApi;
import com.cta4j.bus.prediction.model.Prediction;
import org.jspecify.annotations.NullMarked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

        return this.busApi.vehicles()
                          .findById(vehicleId)
                          .map(VehicleMapper.INSTANCE::toModel)
                          .orElseThrow(() -> new VehicleNotFoundException(vehicleId));
    }

    public List<Arrival> getArrivals(String vehicleId) {
        Objects.requireNonNull(vehicleId);

        this.requireVehicleExists(vehicleId);

        List<Prediction> predictions = this.busApi.predictions()
                                                  .findByVehicleId(vehicleId);

        return predictions.stream()
                          .map(ArrivalMapper.INSTANCE::toModel)
                          .toList();
    }

    private void requireVehicleExists(String vehicleId) {
        this.busApi.vehicles()
                   .findById(vehicleId)
                   .orElseThrow(() -> new VehicleNotFoundException(vehicleId));
    }
}
