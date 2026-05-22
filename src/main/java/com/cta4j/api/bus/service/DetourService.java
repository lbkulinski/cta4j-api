package com.cta4j.api.bus.service;

import com.cta4j.api.bus.mapper.DetourMapper;
import com.cta4j.api.bus.model.Detour;
import com.cta4j.bus.BusApi;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NullMarked
public final class DetourService {
    private final BusApi busApi;

    @Autowired
    public DetourService(BusApi busApi) {
        this.busApi = busApi;
    }

    public List<Detour> getDetours(@Nullable String routeId, @Nullable String direction) {
        List<com.cta4j.bus.detour.model.Detour> detours;

        if (routeId == null && direction == null) {
            detours = this.busApi.detours()
                                 .list();
        } else if (routeId != null && direction == null) {
            detours = this.busApi.detours()
                                 .findByRouteId(routeId);
        } else if (routeId != null) {
            detours = this.busApi.detours()
                                 .findByRouteIdAndDirection(routeId, direction);
        } else {
            throw new IllegalArgumentException("direction cannot be provided without routeId");
        }

        return detours.stream()
                      .map(DetourMapper.INSTANCE::toModel)
                      .toList();
    }
}
