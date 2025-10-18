package app.cta4j.busroute.mapper;

import app.cta4j.busroute.dto.Detour;
import app.cta4j.busroute.dto.DetourRouteDirection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DetourMapper {
    @Mapping(target = "route", source = "route")
    @Mapping(target = "direction", source = "direction")
    DetourRouteDirection toDomainDetourRouteDirection(com.cta4j.bus.model.DetourRouteDirection routeDirection);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "version", source = "version")
    @Mapping(target = "active", source = "active")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "routeDirections", source = "routeDirections")
    @Mapping(target = "startTime", source = "startTime")
    @Mapping(target = "endTime", source = "endTime")
    Detour toDomain(com.cta4j.bus.model.Detour detour);
}
