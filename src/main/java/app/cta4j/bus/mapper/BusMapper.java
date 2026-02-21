package app.cta4j.bus.mapper;

import app.cta4j.bus.dto.BusDto;
import app.cta4j.bus.dto.BusCoordinatesDto;
import app.cta4j.bus.dto.UpcomingBusArrivalDto;
import app.cta4j.common.mapper.CtaMappingHelpers;
import com.cta4j.bus.model.Bus;
import com.cta4j.bus.model.BusCoordinates;
import com.cta4j.bus.model.StopArrival;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CtaMappingHelpers.class)
public interface BusMapper {
    BusCoordinatesDto toDomainCoordinates(BusCoordinates coordinates);

    @Mapping(target = "predictionType", source = "predictionType", qualifiedByName = "toBusPredictionType")
    UpcomingBusArrivalDto toDomainArrival(StopArrival stopArrival);

    BusDto toDomain(Bus bus);
}
