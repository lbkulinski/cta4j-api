package app.cta4j.train.service;

import app.cta4j.train.client.CtaApiClient;
import app.cta4j.train.dto.Location;
import app.cta4j.train.dto.LocationArrival;
import app.cta4j.train.dto.LocationCoordinates;
import app.cta4j.train.external.follow.CtaFollowCtatt;
import app.cta4j.train.external.follow.CtaFollowEta;
import app.cta4j.train.external.follow.CtaFollowPosition;
import app.cta4j.train.external.follow.CtaFollowResponse;
import app.cta4j.train.mapper.LocationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public final class LocationService {
    private final CtaApiClient ctaApiClient;

    private final LocationMapper locationMapper;

    public Location getLocation(int run) {
        if (run <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        CtaFollowResponse response = this.ctaApiClient.followTrain(run);

        if (response == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        CtaFollowCtatt ctatt = response.ctatt();

        if (ctatt == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        CtaFollowPosition ctaPosition = ctatt.position();

        if (ctaPosition == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        List<CtaFollowEta> eta = ctatt.eta();

        if ((eta == null) || eta.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        LocationCoordinates position = this.locationMapper.toDomainCoordinates(ctaPosition);

        List<LocationArrival> arrivals = this.locationMapper.toDomainArrivalList(eta);

        return new Location(position, arrivals);
    }
}
