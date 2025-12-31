package app.cta4j.busroute.service;

import app.cta4j.busroute.dto.DetourDto;
import app.cta4j.busroute.mapper.DetourMapper;
import com.cta4j.bus.client.BusClient;
import com.cta4j.bus.model.Detour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public final class DetourService {
    private final BusClient busClient;
    private final DetourMapper detourMapper;

    @Autowired
    public DetourService(
        BusClient busClient,
        DetourMapper detourMapper
    ) {
        this.busClient = busClient;
        this.detourMapper = detourMapper;
    }

    public List<DetourDto> getDetours(String routeId, String direction) {
        if (routeId == null) {
            throw new IllegalArgumentException("routeId must not be null");
        }

        if (direction == null) {
            throw new IllegalArgumentException("direction must not be null");
        }

        List<Detour> detours = this.busClient.getDetours(routeId, direction);

        if (detours == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return detours.stream()
                      .map(this.detourMapper::toDomain)
                      .toList();
    }
}
