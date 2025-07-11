package app.cta4j.client;

import app.cta4j.dto.ArrivalResponseDto;
import feign.Param;
import feign.RequestLine;

public interface StationArrivalClient {
    @RequestLine("GET /ttarrivals.aspx?mapid={stationId}")
    ArrivalResponseDto getArrivals(@Param String stationId);
}
