package app.cta4j.busroute.mapper;

import app.cta4j.busroute.dto.DetourDto;
import app.cta4j.busroute.dto.DetourRouteDirectionDto;
import com.cta4j.bus.model.Detour;
import com.cta4j.bus.model.DetourRouteDirection;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DetourMapper {
    DetourRouteDirectionDto toDomainDetourRouteDirection(DetourRouteDirection routeDirection);

    DetourDto toDomain(Detour detour);
}
