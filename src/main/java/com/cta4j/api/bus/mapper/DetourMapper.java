package com.cta4j.api.bus.mapper;

import com.cta4j.api.bus.model.Detour;
import org.mapstruct.Mapper;

@Mapper
public interface DetourMapper {
    Detour toModel(com.cta4j.bus.detour.model.Detour detour);
}
