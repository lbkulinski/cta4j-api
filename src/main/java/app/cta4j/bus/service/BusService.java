package app.cta4j.bus.service;

import app.cta4j.bus.dto.Bus;
import app.cta4j.bus.mapper.BusMapper;
import com.cta4j.bus.client.BusClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Optional;

@Service
public final class BusService {
    private final BusClient busClient;

    private final BusMapper busMapper;

    @Autowired
    public BusService(BusClient busClient, BusMapper busMapper) {
        this.busClient = busClient;

        this.busMapper = busMapper;
    }

    public Bus getBus(String id) {
        Objects.requireNonNull(id);

        Optional<com.cta4j.bus.model.Bus> optionalBus = this.busClient.getBus(id);

        if (optionalBus.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        com.cta4j.bus.model.Bus bus = optionalBus.get();

        return this.busMapper.toDomain(bus);
    }
}
