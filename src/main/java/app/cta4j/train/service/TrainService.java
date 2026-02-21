package app.cta4j.train.service;

import app.cta4j.train.dto.TrainDto;
import app.cta4j.train.mapper.TrainMapper;
import com.cta4j.train.client.TrainClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public final class TrainService {
    private final TrainClient trainClient;
    private final TrainMapper trainMapper;

    @Autowired
    public TrainService(
        TrainClient trainClient,
        TrainMapper trainMapper
    ) {
        this.trainClient = trainClient;
        this.trainMapper = trainMapper;
    }

    public TrainDto getTrain(String run) {
        if (run == null) {
            throw new IllegalArgumentException("run must not be null");
        }

        return this.trainClient.getTrain(run)
                               .map(this.trainMapper::toDomain)
                               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
