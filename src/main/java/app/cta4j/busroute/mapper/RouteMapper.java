package app.cta4j.busroute.mapper;

import app.cta4j.busroute.dto.Route;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RouteMapper {
    Route toDomain(app.cta4j.busroute.model.Route route);
}
