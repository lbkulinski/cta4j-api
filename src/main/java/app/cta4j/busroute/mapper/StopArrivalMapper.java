package app.cta4j.busroute.mapper;

import app.cta4j.common.api.external.bus.predictions.CtaPredictionsPrd;
import app.cta4j.busroute.dto.StopArrival;
import app.cta4j.common.mapper.CtaMappingHelpers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CtaMappingHelpers.class)
public interface StopArrivalMapper {
    @Mapping(target = "predictionType", source = "typ", qualifiedByName = "toBusPredictionType")
    @Mapping(target = "stopName", source = "stpnm")
    @Mapping(target = "stopId", source = "stpid", qualifiedByName = "toInt")
    @Mapping(target = "vehicleId", source = "vid", qualifiedByName = "toInt")
    @Mapping(target = "distanceToStop", source = "dstp", qualifiedByName = "toBigInteger")
    @Mapping(target = "route", source = "rt")
    @Mapping(target = "routeDesignator", source = "rtdd")
    @Mapping(target = "routeDirection", source = "rtdir")
    @Mapping(target = "destination", source = "des")
    @Mapping(target = "arrivalTime", source = "prdtm", qualifiedByName= "toBusInstant")
    @Mapping(target = "delayed", source = "dly")
    StopArrival toDomain(CtaPredictionsPrd prd);
}
