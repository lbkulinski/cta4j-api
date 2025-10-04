package app.cta4j.busroute.mapper;

import app.cta4j.api.external.bus.predictions.CtaPredictionsPrd;
import app.cta4j.busroute.dto.StopArrival;
import app.cta4j.mapper.CtaMappingHelpers;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CtaMappingHelpers.class)
public interface ArrivalMapper {
    StopArrival toDomain(CtaPredictionsPrd prd);
}
