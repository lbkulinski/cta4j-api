package app.cta4j.bus.client;

import app.cta4j.bus.dto.BusArrivalResponseDto;
import feign.Param;
import feign.RequestLine;

public interface BusApiClient {
    @RequestLine("GET /bustime/api/v3/getpredictions?rt={routeId}&stpid={stopId}")
    BusArrivalResponseDto getArrivals(@Param String routeId, @Param String stopId);
}
