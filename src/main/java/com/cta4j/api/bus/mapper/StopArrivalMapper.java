package com.cta4j.api.bus.mapper;

import com.cta4j.api.bus.dto.StopArrival;
import com.cta4j.api.common.mapper.CtaMappingHelpers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CtaMappingHelpers.class)
public interface StopArrivalMapper {
    @Mapping(target = "predictionType", source = "predictionType", qualifiedByName = "toBusPredictionType")
    StopArrival toDomain(com.cta4j.bus.model.StopArrival prd);
}
