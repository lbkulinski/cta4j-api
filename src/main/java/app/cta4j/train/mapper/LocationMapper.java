package app.cta4j.train.mapper;

import app.cta4j.mapper.common.CtaMappingHelpers;
import app.cta4j.train.dto.UpcomingArrival;
import app.cta4j.train.dto.Coordinates;
import app.cta4j.client.external.follow.CtaFollowEta;
import app.cta4j.client.external.follow.CtaFollowPosition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = CtaMappingHelpers.class)
public interface LocationMapper {
    @Mapping(target = "latitude", source = "lat", qualifiedByName = "toBigDecimal")
    @Mapping(target = "longitude", source = "lon", qualifiedByName = "toBigDecimal")
    @Mapping(target = "heading", source = "heading", qualifiedByName = "toInt")
    Coordinates toDomainCoordinates(CtaFollowPosition position);

    @Mapping(target = "stationId", source = "staId", qualifiedByName = "toInt")
    @Mapping(target = "stopId", source = "stpId", qualifiedByName = "toInt")
    @Mapping(target = "stationName", source = "staNm")
    @Mapping(target = "stopDescription", source = "stpDe")
    @Mapping(target = "run", source = "rn", qualifiedByName = "toInt")
    @Mapping(target = "route", source = "rt", qualifiedByName = "toTrainRoute")
    @Mapping(target = "destinationStopId", source = "destSt", qualifiedByName = "toInt")
    @Mapping(target = "destinationName", source = "destNm")
    @Mapping(target = "direction", source = "trDr", qualifiedByName = "toInt")
    @Mapping(target = "predictionTime", source = "prdt", qualifiedByName = "toTrainInstant")
    @Mapping(target = "arrivalTime", source = "arrT", qualifiedByName = "toTrainInstant")
    @Mapping(target = "approaching", source = "isApp", qualifiedByName = "toBoolean01")
    @Mapping(target = "scheduled", source = "isSch", qualifiedByName = "toBoolean01")
    @Mapping(target = "delayed", source = "isDly", qualifiedByName = "toBoolean01")
    @Mapping(target = "faulted", source = "isFlt", qualifiedByName = "toBoolean01")
    @Mapping(target = "flags", source = "flags")
    UpcomingArrival toDomainArrival(CtaFollowEta eta);
}
