package app.cta4j.busroute.mapper;

import app.cta4j.busroute.dto.StopArrival;
import app.cta4j.common.mapper.CtaMappingHelpers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CtaMappingHelpers.class)
public interface StopArrivalMapper {
    @Mapping(target = "predictionType", source = "predictionType", qualifiedByName = "toBusPredictionType")
    StopArrival toDomain(com.cta4j.bus.model.StopArrival prd);
}
