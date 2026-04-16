package app.cta4j.train.mapper;

import app.cta4j.common.mapper.CtaMappingHelpers;
import app.cta4j.train.dto.StationArrival;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CtaMappingHelpers.class)
public interface StationArrivalMapper {
    @Mapping(target = "route", source = "route", qualifiedByName = "toTrainRoute")
    StationArrival toDomain(com.cta4j.train.model.StationArrival eta);
}
