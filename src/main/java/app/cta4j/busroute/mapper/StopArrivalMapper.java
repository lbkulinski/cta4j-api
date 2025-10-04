package app.cta4j.busroute.mapper;

import app.cta4j.api.external.bus.predictions.CtaPredictionsPrd;
import app.cta4j.busroute.dto.StopArrival;
import app.cta4j.mapper.CtaMappingHelpers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CtaMappingHelpers.class)
public interface StopArrivalMapper {
    @Mapping(target = "timestamp", source = "tmstmp", qualifiedByName = "toBusInstant")
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
    @Mapping(target = "dynamicActions", source = "dyn", qualifiedByName = "toBoolean01")
    @Mapping(target = "taBlockId", source = "tablockid")
    @Mapping(target = "taTripId", source = "tatripid")
    @Mapping(target = "originalTripNumber", source = "origtatripno")
    @Mapping(target = "minutesAway", source = "prdctdn", qualifiedByName = "toInt")
    @Mapping(target = "zone", source = "zone")
    @Mapping(target = "passengerLoad", source = "psgld", qualifiedByName = "toBusPassengerLoad")
    @Mapping(target = "tripStartSeconds", source = "stst", qualifiedByName= "toInt")
    @Mapping(target = "tripStartDate", source = "stsd")
    @Mapping(target = "flagStop", source = "flagstop", qualifiedByName = "toBusFlagStop")
    StopArrival toDomain(CtaPredictionsPrd prd);
}
