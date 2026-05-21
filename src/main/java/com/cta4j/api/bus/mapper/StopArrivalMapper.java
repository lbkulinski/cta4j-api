package com.cta4j.api.bus.mapper;

import com.cta4j.api.bus.dto.StopArrivalDto;
import com.cta4j.api.bus.model.StopArrival;
import com.cta4j.bus.prediction.model.Prediction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StopArrivalMapper {
    StopArrivalMapper INSTANCE = Mappers.getMapper(StopArrivalMapper.class);

    @Mapping(source = "predictionType", target = "type")
    @Mapping(source = "routeDirection", target = "direction")
    @Mapping(source = "delayed", target = "delayed", qualifiedByName = "toBoolean")
    @Mapping(source = "metadata.dynamicAction", target = "dynamicAction")
    StopArrival toModel(Prediction prediction);

    StopArrivalDto toDto(StopArrival stopArrival);
}
