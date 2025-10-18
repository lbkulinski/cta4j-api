package app.cta4j.train.mapper;

import app.cta4j.common.mapper.CtaMappingHelpers;
import app.cta4j.train.dto.Train;
import app.cta4j.train.dto.TrainCoordinates;
import app.cta4j.train.dto.UpcomingTrainArrival;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CtaMappingHelpers.class)
public interface TrainMapper {
    TrainCoordinates toDomainCoordinates(com.cta4j.train.model.TrainCoordinates coordinates);

    @Mapping(target = "route", source = "route", qualifiedByName = "toTrainRoute")
    UpcomingTrainArrival toDomainArrival(com.cta4j.train.model.UpcomingTrainArrival arrival);

    Train toDomain(com.cta4j.train.model.Train train);
}
