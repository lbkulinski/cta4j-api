package app.cta4j.train.mapper;

import app.cta4j.mapping.common.CtaMappingHelpers;
import app.cta4j.train.dto.TrainArrival;
import app.cta4j.train.external.arrivals.CtaArrivalsEta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CtaMappingHelpers.class)
public interface TrainArrivalMapper {
    @Mapping(target = "stationId", source = "staId", qualifiedByName = "toInt")
    @Mapping(target = "stopId", source = "stpId", qualifiedByName = "toInt")
    @Mapping(target = "stationName", source = "staNm")
    @Mapping(target = "stopDescription", source = "stpDe")
    TrainArrival toDomain(CtaArrivalsEta eta);
}
