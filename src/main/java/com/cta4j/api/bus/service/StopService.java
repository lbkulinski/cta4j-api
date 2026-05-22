package com.cta4j.api.bus.service;

import com.cta4j.api.bus.mapper.StopArrivalMapper;
import com.cta4j.api.bus.model.Stop;
import com.cta4j.api.bus.model.StopArrival;
import com.cta4j.api.bus.repository.StopRepository;
import com.cta4j.bus.BusApi;
import com.cta4j.bus.prediction.model.Prediction;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@NullMarked
public final class StopService {
    private final StopRepository stopRepository;
    private final BusApi busApi;

    @Autowired
    public StopService(
        StopRepository stopRepository,
        BusApi busApi
    ) {
        this.stopRepository = stopRepository;
        this.busApi = busApi;
    }

    public Stop getStop(String stopId) {
        Objects.requireNonNull(stopId);

        return this.stopRepository.getById(stopId);
    }

    public List<StopArrival> getArrivals(String stopId, @Nullable String routeId) {
        Objects.requireNonNull(stopId);

        this.validateStopId(stopId);

        List<Prediction> predictions;

        if (routeId == null) {
            predictions = this.busApi.predictions()
                                     .findByStopId(stopId);
        } else {
            predictions = this.busApi.predictions()
                                     .findByRouteIdAndStopId(routeId, stopId);
        }

        return predictions.stream()
                          .map(StopArrivalMapper.INSTANCE::toModel)
                          .toList();
    }

    private void validateStopId(String stopId) {
        this.stopRepository.getById(stopId);
    }
}
