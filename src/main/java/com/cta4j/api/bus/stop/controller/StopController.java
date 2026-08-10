package com.cta4j.api.bus.stop.controller;

import com.cta4j.api.bus.common.dto.ArrivalDto;
import com.cta4j.api.bus.common.dto.ArrivalsDto;
import com.cta4j.api.bus.common.mapper.ArrivalMapper;
import com.cta4j.api.bus.stop.dto.StopDto;
import com.cta4j.api.bus.stop.mapper.StopMapper;
import com.cta4j.api.bus.stop.model.Stop;
import com.cta4j.api.bus.stop.service.StopService;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bus/stops")
@NullMarked
public final class StopController {
    private final StopService stopService;

    @Autowired
    public StopController(StopService stopService) {
        this.stopService = stopService;
    }

    @GetMapping("/{id}")
    public StopDto getStop(@PathVariable String id) {
        Stop stop = this.stopService.getStop(id);

        return StopMapper.INSTANCE.toDto(stop);
    }

    @GetMapping("/{id}/arrivals")
    public ArrivalsDto getArrivals(
        @PathVariable String id,
        @RequestParam(required = false) @Nullable String routeId
    ) {
        List<ArrivalDto> arrivals = this.stopService.getArrivals(id, routeId)
                                                    .stream()
                                                    .map(ArrivalMapper.INSTANCE::toDto)
                                                    .toList();

        return new ArrivalsDto(arrivals);
    }
}
