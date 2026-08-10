package com.cta4j.api.bus.common.mapper;

import com.cta4j.api.bus.common.dto.ArrivalDto;
import com.cta4j.api.bus.common.model.Arrival;
import com.cta4j.bus.prediction.model.Prediction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = Qualifiers.class)
public interface ArrivalMapper {
    ArrivalMapper INSTANCE = Mappers.getMapper(ArrivalMapper.class);

    @Mapping(target = "type", source = "predictionType")
    @Mapping(target = "direction", source = "routeDirection")
    @Mapping(target = "delayed", source = "delayed", qualifiedByName = "mapBoolean")
    @Mapping(target = "dynamicAction", source = "metadata.dynamicAction")
    Arrival toModel(Prediction prediction);

    ArrivalDto toDto(Arrival arrival);
}
