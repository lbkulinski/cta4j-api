package app.cta4j.train.mapper;

import app.cta4j.common.mapper.CtaMappingHelpers;
import app.cta4j.train.dto.TrainDto;
import app.cta4j.train.dto.TrainCoordinatesDto;
import app.cta4j.train.dto.UpcomingTrainArrivalDto;
import com.cta4j.train.model.Train;
import com.cta4j.train.model.TrainCoordinates;
import com.cta4j.train.model.UpcomingTrainArrival;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CtaMappingHelpers.class)
public interface TrainMapper {
    TrainCoordinatesDto toDomainCoordinates(TrainCoordinates coordinates);

    @Mapping(target = "route", source = "route", qualifiedByName = "toTrainRoute")
    UpcomingTrainArrivalDto toDomainArrival(UpcomingTrainArrival arrival);

    TrainDto toDomain(Train train);
}
