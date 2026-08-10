package com.cta4j.api.bus.detour.controller;

import com.cta4j.api.bus.detour.dto.DetourDto;
import com.cta4j.api.bus.detour.mapper.DetourMapper;
import com.cta4j.api.bus.detour.dto.DetoursDto;
import com.cta4j.api.bus.detour.service.DetourService;
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
    public DetoursDto getDetours(
        @RequestParam(required = false) @Nullable String routeId,
        @RequestParam(required = false) @Nullable String direction
    ) {
        List<DetourDto> detours = this.detourService.getDetours(routeId, direction)
                                                    .stream()
                                                    .map(DetourMapper.INSTANCE::toDto)
                                                    .toList();

        return new DetoursDto(detours);
    }
}
