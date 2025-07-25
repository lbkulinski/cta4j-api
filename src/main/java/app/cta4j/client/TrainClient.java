package app.cta4j.client;

import app.cta4j.dto.LocationResponseDto;
import feign.Param;
import feign.RequestLine;

public interface TrainClient {
    @RequestLine("GET /api/1.0/ttpositions.aspx?rt={route}")
    LocationResponseDto getTrainLocations(@Param String route);
}
