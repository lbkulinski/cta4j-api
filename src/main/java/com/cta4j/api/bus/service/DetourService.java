package com.cta4j.api.bus.service;

import com.cta4j.api.bus.dto.Detour;
import com.cta4j.api.bus.mapper.DetourMapper;
import com.cta4j.bus.client.BusClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
public final class DetourService {
    private final BusClient busClient;

    private final DetourMapper detourMapper;

    @Autowired
    public DetourService(BusClient busClient, DetourMapper detourMapper) {
        this.busClient = busClient;

        this.detourMapper = detourMapper;
    }

    public List<Detour> getDetours(String routeId, String direction) {
        Objects.requireNonNull(routeId);

        Objects.requireNonNull(direction);

        List<com.cta4j.bus.model.Detour> detours = this.busClient.getDetours(routeId, direction);

        if ((detours == null) || detours.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return detours.stream()
                      .map(this.detourMapper::toDomain)
                      .toList();
    }
}
