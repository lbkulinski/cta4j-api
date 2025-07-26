package app.cta4j.train.client;

import app.cta4j.train.dto.TrainArrivalResponseDto;
import feign.Param;
import feign.RequestLine;

public interface TrainApiClient {
    @RequestLine("GET /api/1.0/ttarrivals.aspx?mapid={stationId}")
    TrainArrivalResponseDto getArrivals(@Param String stationId);
}
