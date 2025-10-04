package app.cta4j.train.service;

import app.cta4j.common.api.CtaTrainApi;
import app.cta4j.train.dto.TrainCoordinates;
import app.cta4j.train.dto.TrainLocation;
import app.cta4j.train.dto.UpcomingTrainArrival;
import app.cta4j.common.api.external.train.follow.CtaFollowCtatt;
import app.cta4j.common.api.external.train.follow.CtaFollowEta;
import app.cta4j.common.api.external.train.follow.CtaFollowPosition;
import app.cta4j.common.api.external.train.follow.CtaFollowResponse;
import app.cta4j.train.mapper.TrainLocationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public final class TrainLocationService {
    private final CtaTrainApi ctaTrainApi;

    private final TrainLocationMapper trainLocationMapper;

    @Autowired
    public TrainLocationService(CtaTrainApi ctaTrainApi, TrainLocationMapper trainLocationMapper) {
        this.ctaTrainApi = ctaTrainApi;

        this.trainLocationMapper = trainLocationMapper;
    }

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

        TrainCoordinates position = this.trainLocationMapper.toDomainCoordinates(ctaPosition);

        List<UpcomingTrainArrival> arrivals = eta.stream()
                                                 .map(this.trainLocationMapper::toDomainArrival)
                                                 .toList();

        return new TrainLocation(position, arrivals);
    }
}
