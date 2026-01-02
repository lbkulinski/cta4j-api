package app.cta4j.busroute.mapper;

import app.cta4j.busroute.dto.StopDto;
import app.cta4j.busroute.model.Stop;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StopMapper {
    StopDto toDomain(Stop stop);
}
