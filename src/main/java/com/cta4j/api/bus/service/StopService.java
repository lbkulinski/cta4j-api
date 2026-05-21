package com.cta4j.api.bus.service;

import com.cta4j.api.bus.model.Stop;
import com.cta4j.api.bus.repository.StopRepository;
import org.jspecify.annotations.NullMarked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@NullMarked
public final class StopService {
    private final StopRepository stopRepository;

    @Autowired
    public StopService(StopRepository stopRepository) {
        this.stopRepository = stopRepository;
    }

    public Stop getStop(String id) {
        Objects.requireNonNull(id);

        return this.stopRepository.getById(id);
    }
}
