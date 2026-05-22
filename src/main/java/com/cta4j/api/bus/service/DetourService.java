package com.cta4j.api.bus.service;

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
        return null;
    }
}
