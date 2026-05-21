package com.cta4j.api.bus.controller;

import com.cta4j.api.bus.dto.StopDto;
import com.cta4j.api.bus.mapper.StopMapper;
import com.cta4j.api.bus.model.Stop;
import com.cta4j.api.bus.response.StopResponse;
import com.cta4j.api.bus.service.StopService;
import org.jspecify.annotations.NullMarked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public StopResponse getStop(@PathVariable String id) {
        Stop stop = this.stopService.getStop(id);

        StopDto stopDto = StopMapper.INSTANCE.toDto(stop);

        return new StopResponse(stopDto);
    }
}
