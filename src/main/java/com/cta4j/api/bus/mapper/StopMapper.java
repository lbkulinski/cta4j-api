package com.cta4j.api.bus.mapper;

import com.cta4j.api.bus.dto.Stop;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StopMapper {
    Stop toDomain(com.cta4j.api.bus.model.Stop stop);
}
