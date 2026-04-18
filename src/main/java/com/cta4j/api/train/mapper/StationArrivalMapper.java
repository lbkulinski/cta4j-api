package com.cta4j.api.train.mapper;

import com.cta4j.api.common.mapper.CtaMappingHelpers;
import com.cta4j.api.train.dto.StationArrival;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CtaMappingHelpers.class)
public interface StationArrivalMapper {
    @Mapping(target = "route", source = "route", qualifiedByName = "toTrainRoute")
    StationArrival toDomain(com.cta4j.train.model.StationArrival eta);
}
