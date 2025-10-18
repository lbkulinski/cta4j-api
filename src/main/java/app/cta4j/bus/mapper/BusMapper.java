package app.cta4j.bus.mapper;

import app.cta4j.bus.dto.Bus;
import app.cta4j.bus.dto.BusCoordinates;
import app.cta4j.bus.dto.UpcomingBusArrival;
import app.cta4j.common.mapper.CtaMappingHelpers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CtaMappingHelpers.class)
public interface BusMapper {
    BusCoordinates toDomainCoordinates(com.cta4j.bus.model.BusCoordinates coordinates);

    @Mapping(target = "predictionType", source = "predictionType", qualifiedByName = "toBusPredictionType")
    UpcomingBusArrival toDomainArrival(com.cta4j.bus.model.StopArrival prd);

    Bus toDomain(com.cta4j.bus.model.Bus bus);
}
