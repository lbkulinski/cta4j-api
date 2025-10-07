package app.cta4j.common.api;

import app.cta4j.common.api.external.bus.detours.CtaDetoursResponse;
import app.cta4j.common.api.external.bus.predictions.CtaPredictionsResponse;
import feign.Param;
import feign.RequestLine;

public interface CtaBusApi {
    @RequestLine("GET /bustime/api/v3/getpredictions?rt={routeId}&stpid={stopId}")
    CtaPredictionsResponse getPredictions(@Param String routeId, @Param int stopId);

    @RequestLine("GET /bustime/api/v3/getdetours?rt={routeId}&rtdir={direction}")
    CtaDetoursResponse getDetours(@Param String routeId, @Param String direction);
}
