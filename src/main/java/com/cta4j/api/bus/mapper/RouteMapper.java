package com.cta4j.api.bus.mapper;

import com.cta4j.api.bus.dto.Route;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RouteMapper {
    Route toDomain(com.cta4j.api.bus.model.Route route);
}
