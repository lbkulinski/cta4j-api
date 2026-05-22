package com.cta4j.api.bus.controller;

import com.cta4j.api.bus.dto.DetourDto;
import com.cta4j.api.bus.mapper.DetourMapper;
import com.cta4j.api.bus.response.DetoursResponse;
import com.cta4j.api.bus.service.DetourService;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bus/detours")
@NullMarked
public final class DetourController {
    private final DetourService detourService;

    @Autowired
    public DetourController(DetourService detourService) {
        this.detourService = detourService;
    }

    @GetMapping
    public DetoursResponse getDetours(
        @RequestParam(required = false) @Nullable String routeId,
        @RequestParam(required = false) @Nullable String direction
    ) {
        List<DetourDto> detours = this.detourService.getDetours(routeId, direction)
                                                    .stream()
                                                    .map(DetourMapper.INSTANCE::toDto)
                                                    .toList();

        return new DetoursResponse(detours);
    }
}
