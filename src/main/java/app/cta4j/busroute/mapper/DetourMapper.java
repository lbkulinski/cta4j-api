package app.cta4j.busroute.mapper;

import app.cta4j.busroute.dto.Detour;
import app.cta4j.busroute.dto.DetourRouteDirection;
import app.cta4j.common.api.external.bus.detours.CtaDetour;
import app.cta4j.common.api.external.bus.detours.CtaDetoursRouteDirection;
import app.cta4j.common.mapper.CtaMappingHelpers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CtaMappingHelpers.class)
public interface DetourMapper {
    @Mapping(target = "route", source = "rt")
    @Mapping(target = "direction", source = "dir")
    DetourRouteDirection toDomainDetourRouteDirection(CtaDetoursRouteDirection routeDirection);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "version", source = "ver")
    @Mapping(target = "active", source = "st", qualifiedByName = "toBoolean01")
    @Mapping(target = "description", source = "desc")
    @Mapping(target = "routeDirections", source = "rtdirs")
    @Mapping(target = "startDate", source = "startdt", qualifiedByName = "toBusInstant")
    @Mapping(target = "endDate", source = "enddt", qualifiedByName = "toBusInstant")
    Detour toDomainDetour(CtaDetour detour);
}
