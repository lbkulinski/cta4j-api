package com.cta4j.api.bus.vehicle.mapper;

import com.cta4j.api.bus.vehicle.dto.VehicleDto;
import com.cta4j.api.bus.vehicle.model.Vehicle;
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
