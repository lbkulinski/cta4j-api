package com.cta4j.api.bus.route.mapper;

import com.cta4j.api.bus.route.dto.RouteStopDto;
import com.cta4j.api.bus.route.model.RouteStop;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RouteStopMapper {
    RouteStopMapper INSTANCE = Mappers.getMapper(RouteStopMapper.class);

    RouteStopDto toDto(RouteStop stop);
}
