package com.cta4j.api.train.mapper;

import com.cta4j.api.train.dto.Station;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StationMapper {
    Station toDomain(com.cta4j.api.train.model.Station station);
}
