package app.cta4j.busroute.service;

import app.cta4j.api.CtaBusApi;
import app.cta4j.api.external.bus.predictions.CtaPredictionsBustimeResponse;
import app.cta4j.api.external.bus.predictions.CtaPredictionsPrd;
import app.cta4j.api.external.bus.predictions.CtaPredictionsResponse;
import app.cta4j.busroute.dto.StopArrival;
import app.cta4j.busroute.mapper.StopArrivalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
public final class StopArrivalService {
    private final CtaBusApi ctaBusApi;

    private final StopArrivalMapper stopArrivalMapper;

    @Autowired
    public StopArrivalService(CtaBusApi ctaBusApi, StopArrivalMapper stopArrivalMapper) {
        this.ctaBusApi = ctaBusApi;

        this.stopArrivalMapper = stopArrivalMapper;
    }

    public List<StopArrival> getArrivals(String route, int stopId) {
        Objects.requireNonNull(route);

        if (stopId <= 0) {
            throw new IllegalArgumentException("stopId must be positive");
        }

        CtaPredictionsResponse response = this.ctaBusApi.getPredictions(route, stopId);

        if (response == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        CtaPredictionsBustimeResponse bustimeResponse = response.bustimeResponse();

        if (bustimeResponse == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        List<CtaPredictionsPrd> prd = bustimeResponse.prd();

        if ((prd == null) || prd.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return prd.stream()
                  .map(this.stopArrivalMapper::toDomain)
                  .toList();
    }
}
