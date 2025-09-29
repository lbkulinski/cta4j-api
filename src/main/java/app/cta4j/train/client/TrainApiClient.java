package app.cta4j.train.client;

import app.cta4j.train.external.arrivals.CtaArrivalsResponse;
import app.cta4j.train.external.follow.CtaFollowResponse;
import feign.Param;
import feign.RequestLine;

public interface TrainApiClient {
    @RequestLine("GET /api/1.0/ttarrivals.aspx?mapid={stationId}")
    CtaArrivalsResponse getArrivals(@Param String stationId);

    @RequestLine("GET /api/1.0/ttfollow.aspx?runnumber={run}")
    CtaFollowResponse followTrain(@Param int run);
}
