package com.cta4j.api.bus.mapper;

import com.cta4j.api.bus.dto.DetourDto;
import com.cta4j.api.bus.model.Detour;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DetourMapper {
    DetourMapper INSTANCE = Mappers.getMapper(DetourMapper.class);

    Detour toModel(com.cta4j.bus.detour.model.Detour detour);

    DetourDto toDto(Detour detour);
}
