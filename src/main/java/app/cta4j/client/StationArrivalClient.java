package app.cta4j.client;

import app.cta4j.dto.StationArrivalResponseDto;
import feign.Param;
import feign.RequestLine;

public interface StationArrivalClient {
    @RequestLine("GET /api/1.0/ttarrivals.aspx?mapid={stationId}")
    StationArrivalResponseDto getArrivals(@Param String stationId);
}
