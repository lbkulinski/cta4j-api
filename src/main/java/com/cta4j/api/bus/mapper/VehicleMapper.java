package com.cta4j.api.bus.mapper;

import com.cta4j.api.bus.dto.VehicleDto;
import com.cta4j.api.bus.model.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VehicleMapper {
    VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);

    @Mapping(target = "lastUpdated", source = "metadata.lastUpdated")
    @Mapping(target = "speed", source = "metadata.speed")
    @Mapping(target = "passengerLoad", source = "metadata.passengerLoad")
    @Mapping(target = "stopId", source = "metadata.stopId")
    Vehicle toModel(com.cta4j.bus.vehicle.model.Vehicle vehicle);

    VehicleDto toDto(Vehicle vehicle);
}
