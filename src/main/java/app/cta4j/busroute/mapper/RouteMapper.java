package app.cta4j.busroute.mapper;

import app.cta4j.busroute.dto.RouteDto;
import app.cta4j.busroute.model.Route;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RouteMapper {
    RouteDto toDomain(Route route);
}
