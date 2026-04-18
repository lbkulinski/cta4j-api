package com.cta4j.api.bus.mapper;

import com.cta4j.api.bus.dto.Detour;
import com.cta4j.api.bus.dto.DetourRouteDirection;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DetourMapper {
    DetourRouteDirection toDomainDetourRouteDirection(com.cta4j.bus.model.DetourRouteDirection routeDirection);

    Detour toDomain(com.cta4j.bus.model.Detour detour);
}
