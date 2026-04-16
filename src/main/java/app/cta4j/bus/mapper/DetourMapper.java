package app.cta4j.bus.mapper;

import app.cta4j.bus.dto.Detour;
import app.cta4j.bus.dto.DetourRouteDirection;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DetourMapper {
    DetourRouteDirection toDomainDetourRouteDirection(com.cta4j.bus.model.DetourRouteDirection routeDirection);

    Detour toDomain(com.cta4j.bus.model.Detour detour);
}
