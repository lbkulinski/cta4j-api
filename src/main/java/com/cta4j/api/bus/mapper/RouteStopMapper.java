package com.cta4j.api.bus.mapper;

import com.cta4j.api.bus.dto.RouteStopDto;
import com.cta4j.api.bus.model.RouteStop;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RouteStopMapper {
    RouteStopMapper INSTANCE = Mappers.getMapper(RouteStopMapper.class);

    RouteStopDto toDto(RouteStop stop);
}
