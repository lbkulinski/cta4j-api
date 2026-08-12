package com.cta4j.api.bus.stop.service;

import com.cta4j.api.bus.common.mapper.ArrivalMapper;
import com.cta4j.api.bus.common.model.Arrival;
import com.cta4j.api.bus.stop.exception.StopNotFoundException;
import com.cta4j.api.bus.stop.model.Stop;
import com.cta4j.api.bus.stop.repository.StopRepository;
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
    public StopService(StopRepository stopRepository, BusApi busApi) {
        this.stopRepository = stopRepository;
        this.busApi = busApi;
    }

    public Stop getStop(String stopId) {
        Objects.requireNonNull(stopId);

        return this.stopRepository.getById(stopId)
                                  .orElseThrow(() -> new StopNotFoundException(stopId));
    }

    public List<Arrival> getArrivals(String stopId, @Nullable String routeId) {
        Objects.requireNonNull(stopId);

        //throw if stopId does not exist
        this.getStop(stopId);

        List<Prediction> predictions;

        if (routeId == null) {
            predictions = this.busApi.predictions()
                                     .findByStopId(stopId);
        } else {
            predictions = this.busApi.predictions()
                                     .findByRouteIdAndStopId(routeId, stopId);
        }

        return predictions.stream()
                          .map(ArrivalMapper.INSTANCE::toModel)
                          .toList();
    }
}
