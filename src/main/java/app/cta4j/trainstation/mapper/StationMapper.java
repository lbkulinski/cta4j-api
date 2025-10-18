package app.cta4j.trainstation.mapper;

import app.cta4j.trainstation.dto.Station;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StationMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    Station toDomain(app.cta4j.trainstation.model.Station station);
}
