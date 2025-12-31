package app.cta4j.bus.service;

import app.cta4j.bus.dto.BusDto;
import app.cta4j.bus.mapper.BusMapper;
import com.cta4j.bus.client.BusClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public final class BusService {
    private final BusClient busClient;
    private final BusMapper busMapper;

    @Autowired
    public BusService(
        BusClient busClient,
        BusMapper busMapper
    ) {
        this.busClient = busClient;
        this.busMapper = busMapper;
    }

    public BusDto getBus(String id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }

        return this.busClient.getBus(id)
                             .map(this.busMapper::toDomain)
                             .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
