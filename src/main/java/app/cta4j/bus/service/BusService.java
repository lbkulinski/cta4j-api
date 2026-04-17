package app.cta4j.bus.service;

import app.cta4j.bus.dto.BusResponse;
import app.cta4j.bus.exception.BusNotFoundException;
import com.cta4j.bus.BusApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public final class BusService {
    private final BusApi busApi;

    @Autowired
    public BusService(BusApi busApi) {
        this.busApi = busApi;
    }

    public BusResponse getBus(String id) {
        Objects.requireNonNull(id, "id must not be null");

        return this.busApi.vehicles()
                          .findById(id)
                          .orElseThrow(() -> new BusNotFoundException("Bus with id '%s' not found".formatted(id)));
    }
}
