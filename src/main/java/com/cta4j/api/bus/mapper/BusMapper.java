package com.cta4j.api.bus.mapper;

import com.cta4j.api.bus.dto.BusResponse;
import com.cta4j.api.common.mapper.CtaMappingHelpers;
import com.cta4j.bus.vehicle.model.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = CtaMappingHelpers.class)
public interface BusMapper {
    BusMapper INSTANCE = Mappers.getMapper(BusMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "route", source = "route")
    @Mapping(target = "destination", source = "destination")
    @Mapping(target = "delayed", source = "delayed")
    @Mapping(target = "coordinates", source = "coordinates")
    BusResponse toResponse(Vehicle vehicle);
}
