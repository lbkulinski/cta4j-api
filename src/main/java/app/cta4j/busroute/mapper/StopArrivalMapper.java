package app.cta4j.busroute.mapper;

import app.cta4j.busroute.dto.StopArrivalDto;
import app.cta4j.common.mapper.CtaMappingHelpers;
import com.cta4j.bus.model.StopArrival;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CtaMappingHelpers.class)
public interface StopArrivalMapper {
    @Mapping(target = "predictionType", source = "predictionType", qualifiedByName = "toBusPredictionType")
    StopArrivalDto toDomain(StopArrival prd);
}
