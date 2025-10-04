package app.cta4j.common.api;

import app.cta4j.common.api.external.train.arrivals.CtaArrivalsResponse;
import app.cta4j.common.api.external.train.follow.CtaFollowResponse;
import feign.Param;
import feign.RequestLine;

public interface CtaTrainApi {
    @RequestLine("GET /api/1.0/ttarrivals.aspx?mapid={stationId}")
    CtaArrivalsResponse getArrivals(@Param int stationId);

    @RequestLine("GET /api/1.0/ttfollow.aspx?runnumber={run}")
    CtaFollowResponse followTrain(@Param int run);
}
