package app.cta4j.bus.mapper;

import app.cta4j.bus.dto.Stop;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StopMapper {
    Stop toDomain(app.cta4j.bus.model.Stop stop);
}
