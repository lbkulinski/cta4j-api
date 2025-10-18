package app.cta4j.busroute.mapper;

import app.cta4j.busroute.dto.StopArrival;
import app.cta4j.common.mapper.CtaMappingHelpers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CtaMappingHelpers.class)
public interface StopArrivalMapper {
    @Mapping(target = "predictionType", source = "predictionType", qualifiedByName = "toBusPredictionType")
    @Mapping(target = "stopName", source = "stopName")
    @Mapping(target = "stopId", source = "stopId")
    @Mapping(target = "vehicleId", source = "vehicleId")
    @Mapping(target = "distanceToStop", source = "distanceToStop")
    @Mapping(target = "route", source = "route")
    @Mapping(target = "routeDesignator", source = "routeDesignator")
    @Mapping(target = "routeDirection", source = "routeDirection")
    @Mapping(target = "destination", source = "destination")
    @Mapping(target = "arrivalTime", source = "arrivalTime")
    @Mapping(target = "delayed", source = "delayed")
    StopArrival toDomain(com.cta4j.model.bus.StopArrival prd);
}
