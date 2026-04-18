package com.cta4j.api.bus.service;

import com.cta4j.api.bus.dto.BusResponse;
import com.cta4j.api.bus.exception.BusNotFoundException;
import com.cta4j.api.bus.mapper.BusMapper;
import com.cta4j.bus.BusApi;
import org.jspecify.annotations.NullMarked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@NullMarked
public final class BusService {
    private final BusApi busApi;

    @Autowired
    public BusService(BusApi busApi) {
        this.busApi = busApi;
    }

    public BusResponse getBus(String id) {
        Objects.requireNonNull(id, "id must not be null");

        return this.busApi.vehicles()
                          .findById(id)
                          .map(BusMapper.INSTANCE::toResponse)
                          .orElseThrow(() -> new BusNotFoundException("Bus with id '%s' not found".formatted(id)));
    }
}
