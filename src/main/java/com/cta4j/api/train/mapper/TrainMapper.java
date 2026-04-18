package com.cta4j.api.train.mapper;

import com.cta4j.api.common.mapper.CtaMappingHelpers;
import com.cta4j.api.train.dto.Train;
import com.cta4j.api.train.dto.TrainCoordinates;
import com.cta4j.api.train.dto.UpcomingTrainArrival;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CtaMappingHelpers.class)
public interface TrainMapper {
    TrainCoordinates toDomainCoordinates(com.cta4j.train.model.TrainCoordinates coordinates);

    @Mapping(target = "route", source = "route", qualifiedByName = "toTrainRoute")
    UpcomingTrainArrival toDomainArrival(com.cta4j.train.model.UpcomingTrainArrival arrival);

    Train toDomain(com.cta4j.train.model.Train train);
}
