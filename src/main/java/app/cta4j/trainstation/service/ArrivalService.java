package app.cta4j.trainstation.service;

import app.cta4j.api.CtaTrainApi;
import app.cta4j.trainstation.dto.StationArrival;
import app.cta4j.api.external.train.arrivals.CtaArrivalsCtatt;
import app.cta4j.api.external.train.arrivals.CtaArrivalsEta;
import app.cta4j.api.external.train.arrivals.CtaArrivalsResponse;
import app.cta4j.trainstation.mapper.ArrivalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public final class ArrivalService {
    private final CtaTrainApi ctaTrainApi;

    private final ArrivalMapper arrivalMapper;

    @Autowired
    public ArrivalService(CtaTrainApi ctaTrainApi, ArrivalMapper arrivalMapper) {
        this.ctaTrainApi = ctaTrainApi;

        this.arrivalMapper = arrivalMapper;
    }

    public List<StationArrival> getArrivals(int stationId) {
        CtaArrivalsResponse response = this.ctaTrainApi.getArrivals(stationId);

        if (response == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        CtaArrivalsCtatt ctatt = response.ctatt();

        if (ctatt == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        List<CtaArrivalsEta> eta = ctatt.eta();

        if ((eta == null) || eta.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        List<StationArrival> arrivals = eta.stream()
                                           .map(this.arrivalMapper::toDomain)
                                           .toList();

        return List.copyOf(arrivals);
    }
}
