package com.cta4j.api.bus.mapper;

import com.cta4j.api.bus.dto.StopArrivalDto;
import com.cta4j.api.bus.model.StopArrival;
import com.cta4j.api.common.mapper.CtaMappingHelpers;
import com.cta4j.bus.prediction.model.Prediction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = CtaMappingHelpers.class)
public interface StopArrivalMapper {
    StopArrivalMapper INSTANCE = Mappers.getMapper(StopArrivalMapper.class);

    @Mapping(target = "type", source = "predictionType")
    @Mapping(target = "direction", source = "routeDirection")
    @Mapping(target = "delayed", source = "delayed", qualifiedByName = "toBoolean")
    @Mapping(target = "dynamicAction", source = "metadata.dynamicAction")
    StopArrival toModel(Prediction prediction);

    StopArrivalDto toDto(StopArrival stopArrival);
}
