package app.cta4j.train.mapper;

import app.cta4j.train.dto.Station;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StationMapper {
    Station toDomain(app.cta4j.train.model.Station station);
}
