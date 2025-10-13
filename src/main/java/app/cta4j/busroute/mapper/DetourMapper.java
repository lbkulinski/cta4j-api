package app.cta4j.busroute.mapper;

import app.cta4j.busroute.dto.Detour;
import app.cta4j.busroute.dto.DetourRouteDirection;
import app.cta4j.common.mapper.CtaMappingHelpers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CtaMappingHelpers.class)
public interface DetourMapper {
    @Mapping(target = "route", source = "route")
    @Mapping(target = "direction", source = "direction")
    DetourRouteDirection toDomainDetourRouteDirection(com.cta4j.model.bus.DetourRouteDirection routeDirection);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "version", source = "version")
    @Mapping(target = "active", source = "active")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "routeDirections", source = "routeDirections")
    @Mapping(target = "startDate", source = "startDate")
    @Mapping(target = "endDate", source = "endDate")
    Detour toDomainDetour(com.cta4j.model.bus.Detour detour);
}
