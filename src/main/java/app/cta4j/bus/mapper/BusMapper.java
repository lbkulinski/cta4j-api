package app.cta4j.bus.mapper;

import app.cta4j.bus.dto.Bus;
import app.cta4j.bus.dto.BusCoordinates;
import app.cta4j.bus.dto.UpcomingBusArrival;
import app.cta4j.common.mapper.CtaMappingHelpers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CtaMappingHelpers.class)
public interface BusMapper {
    @Mapping(target = "latitude", source = "latitude")
    @Mapping(target = "longitude", source = "longitude")
    @Mapping(target = "heading", source = "heading")
    BusCoordinates toDomainCoordinates(com.cta4j.bus.model.BusCoordinates coordinates);

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
    UpcomingBusArrival toDomainArrival(com.cta4j.bus.model.StopArrival prd);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "route", source = "route")
    @Mapping(target = "destination", source = "destination")
    @Mapping(target = "coordinates", source = "coordinates")
    @Mapping(target = "arrivals", source = "arrivals")
    @Mapping(target = "delayed", source = "delayed")
    Bus toDomain(com.cta4j.bus.model.Bus bus);
}
