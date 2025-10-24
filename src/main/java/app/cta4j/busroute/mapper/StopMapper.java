package app.cta4j.busroute.mapper;

import app.cta4j.busroute.dto.Stop;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StopMapper {
    Stop toDomain(app.cta4j.busroute.model.Stop stop);
}
