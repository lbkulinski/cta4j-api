package app.cta4j.trainstation.mapper;

import app.cta4j.common.mapper.CtaMappingHelpers;
import app.cta4j.trainstation.dto.StationArrivalDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CtaMappingHelpers.class)
public interface StationArrivalMapper {
    @Mapping(target = "route", source = "route", qualifiedByName = "toTrainRoute")
    StationArrivalDto toDomain(com.cta4j.train.model.StationArrival eta);
}
