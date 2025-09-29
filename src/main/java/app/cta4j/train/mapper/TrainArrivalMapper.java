package app.cta4j.train.mapper;

import app.cta4j.train.dto.TrainArrival;
import app.cta4j.train.external.arrivals.CtaArrivalsEta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TrainArrivalMapper {
    @Mapping(target = "stationId", source = "staId")
    TrainArrival toDomain(CtaArrivalsEta eta);
}
