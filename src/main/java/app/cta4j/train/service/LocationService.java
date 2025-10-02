package app.cta4j.train.service;

import app.cta4j.client.CtaTrainApi;
import app.cta4j.train.dto.Coordinates;
import app.cta4j.train.dto.TrainLocation;
import app.cta4j.train.dto.UpcomingArrival;
import app.cta4j.client.external.follow.CtaFollowCtatt;
import app.cta4j.client.external.follow.CtaFollowEta;
import app.cta4j.client.external.follow.CtaFollowPosition;
import app.cta4j.client.external.follow.CtaFollowResponse;
import app.cta4j.train.mapper.LocationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public final class LocationService {
    private final CtaTrainApi ctaTrainApi;

    private final LocationMapper locationMapper;

    public TrainLocation getLocation(int run) {
        if (run <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        CtaFollowResponse response = this.ctaTrainApi.followTrain(run);

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

        Coordinates position = this.locationMapper.toDomainCoordinates(ctaPosition);

        List<UpcomingArrival> arrivals = eta.stream()
                                            .map(this.locationMapper::toDomainArrival)
                                            .toList();

        return new TrainLocation(position, arrivals);
    }
}
