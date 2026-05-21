package com.cta4j.api.bus.mapper;

import com.cta4j.api.bus.dto.StopDto;
import com.cta4j.api.bus.model.Stop;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StopMapper {
    StopMapper INSTANCE = Mappers.getMapper(StopMapper.class);

    StopDto toDto(Stop stop);
}
