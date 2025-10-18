package app.cta4j.train.mapper;

import app.cta4j.common.mapper.CtaMappingHelpers;
import app.cta4j.train.dto.TrainCoordinates;
import app.cta4j.train.dto.UpcomingTrainArrival;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CtaMappingHelpers.class)
public interface TrainMapper {
    @Mapping(target = "latitude", source = "latitude")
    @Mapping(target = "longitude", source = "longitude")
    @Mapping(target = "heading", source = "heading")
    TrainCoordinates toDomainCoordinates(com.cta4j.model.train.TrainCoordinates coordinates);

    @Mapping(target = "stationId", source = "stationId")
    @Mapping(target = "stopId", source = "stopId")
    @Mapping(target = "stationName", source = "stationName")
    @Mapping(target = "stopDescription", source = "stopDescription")
    @Mapping(target = "run", source = "run")
    @Mapping(target = "route", source = "route", qualifiedByName = "toTrainRoute")
    @Mapping(target = "destinationStopId", source = "destinationStopId")
    @Mapping(target = "destinationName", source = "destinationName")
    @Mapping(target = "direction", source = "direction")
    @Mapping(target = "predictionTime", source = "predictionTime")
    @Mapping(target = "arrivalTime", source = "arrivalTime")
    @Mapping(target = "approaching", source = "approaching")
    @Mapping(target = "scheduled", source = "scheduled")
    @Mapping(target = "delayed", source = "delayed")
    @Mapping(target = "faulted", source = "faulted")
    @Mapping(target = "flags", source = "flags")
    UpcomingTrainArrival toDomainArrival(com.cta4j.model.train.UpcomingTrainArrival arrival);
}
