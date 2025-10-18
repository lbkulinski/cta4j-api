package app.cta4j.trainstation.mapper;

import app.cta4j.trainstation.dto.Station;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StationMapper {
    Station toDomain(app.cta4j.trainstation.model.Station station);
}
