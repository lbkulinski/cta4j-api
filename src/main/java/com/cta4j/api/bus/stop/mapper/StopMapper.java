package com.cta4j.api.bus.stop.mapper;

import com.cta4j.api.bus.stop.dto.StopDto;
import com.cta4j.api.bus.stop.model.Stop;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StopMapper {
    StopMapper INSTANCE = Mappers.getMapper(StopMapper.class);

    StopDto toDto(Stop stop);
}
