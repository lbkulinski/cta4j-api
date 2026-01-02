package app.cta4j.trainstation.mapper;

import app.cta4j.trainstation.dto.StationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StationMapper {
    StationDto toDomain(app.cta4j.trainstation.model.Station station);
}
