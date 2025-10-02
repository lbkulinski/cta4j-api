package app.cta4j.station.service;

import app.cta4j.api.CtaTrainApi;
import app.cta4j.station.dto.StationArrivalDto;
import app.cta4j.api.external.train.arrivals.CtaArrivalsCtatt;
import app.cta4j.api.external.train.arrivals.CtaArrivalsEta;
import app.cta4j.api.external.train.arrivals.CtaArrivalsResponse;
import app.cta4j.station.mapper.ArrivalMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public final class ArrivalService {
    private final CtaTrainApi ctaTrainApi;

    private final ArrivalMapper arrivalMapper;

    public List<StationArrivalDto> getArrivals(int stationId) {
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

        List<StationArrivalDto> arrivals = eta.stream()
                                              .map(this.arrivalMapper::toDomain)
                                              .toList();

        return List.copyOf(arrivals);
    }
}
