package app.cta4j.api;

import app.cta4j.api.external.bus.predictions.CtaPredictionsResponse;
import feign.Param;
import feign.RequestLine;

public interface CtaBusApi {
    @RequestLine("GET /bustime/api/v3/getpredictions?rt={route}&stpid={stopId}")
    CtaPredictionsResponse getPredictions(@Param String route, @Param int stopId);
}
