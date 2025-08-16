package app.cta4j.bus.client;

import app.cta4j.bus.dto.BusArrivalResponseDto;
import app.cta4j.bus.dto.BusDetourResponseDto;
import feign.Param;
import feign.RequestLine;

public interface BusApiClient {
    @RequestLine("GET /bustime/api/v3/getpredictions?rt={routeId}&stpid={stopId}")
    BusArrivalResponseDto getArrivals(@Param String routeId, @Param String stopId);

    @RequestLine("GET /bustime/api/v3/getdetours?rt={routeId}&rtdir={direction}")
    BusDetourResponseDto getDetours(@Param String routeId, @Param String direction);
}
