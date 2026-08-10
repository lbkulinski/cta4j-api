package com.cta4j.api.bus.route.mapper;

import com.cta4j.api.bus.route.dto.RouteDto;
import com.cta4j.api.bus.route.model.Route;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RouteMapper {
    RouteMapper INSTANCE = Mappers.getMapper(RouteMapper.class);

    RouteDto toDto(Route route);
}
