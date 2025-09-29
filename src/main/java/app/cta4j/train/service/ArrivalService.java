package app.cta4j.train.service;

import app.cta4j.train.client.CtaApiClient;
import app.cta4j.train.dto.StationArrival;
import app.cta4j.train.external.arrivals.CtaArrivalsCtatt;
import app.cta4j.train.external.arrivals.CtaArrivalsEta;
import app.cta4j.train.external.arrivals.CtaArrivalsResponse;
import app.cta4j.train.mapper.ArrivalMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public final class ArrivalService {
    private final CtaApiClient ctaApiClient;

    private final ArrivalMapper arrivalMapper;

    public List<StationArrival> getArrivals(String stationId) {
        Objects.requireNonNull(stationId);

        CtaArrivalsResponse response = this.ctaApiClient.getArrivals(stationId);

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

        List<StationArrival> arrivals = this.arrivalMapper.toDomainList(eta);

        return List.copyOf(arrivals);
    }
}
