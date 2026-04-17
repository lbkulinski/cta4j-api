package app.cta4j.bus.mapper;

import app.cta4j.common.mapper.CtaMappingHelpers;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = CtaMappingHelpers.class)
public interface BusMapper {
    BusMapper INSTANCE = Mappers.getMapper(BusMapper.class);


}
