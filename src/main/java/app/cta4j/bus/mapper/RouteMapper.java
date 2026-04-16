package app.cta4j.bus.mapper;

import app.cta4j.bus.dto.Route;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RouteMapper {
    Route toDomain(app.cta4j.bus.model.Route route);
}
