package app.cta4j.busroute.service;

import app.cta4j.busroute.dto.Detour;
import app.cta4j.busroute.mapper.DetourMapper;
import app.cta4j.common.api.CtaBusApi;
import app.cta4j.common.api.external.bus.detours.CtaDetour;
import app.cta4j.common.api.external.bus.detours.CtaDetoursBustimeResponse;
import app.cta4j.common.api.external.bus.detours.CtaDetoursResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
public final class DetourService {
    private final CtaBusApi ctaBusApi;

    private final DetourMapper detourMapper;

    @Autowired
    public DetourService(CtaBusApi ctaBusApi, DetourMapper detourMapper) {
        this.ctaBusApi = ctaBusApi;

        this.detourMapper = detourMapper;
    }

    public List<Detour> getDetours(String routeId, String direction) {
        Objects.requireNonNull(routeId);

        Objects.requireNonNull(direction);

        CtaDetoursResponse response = this.ctaBusApi.getDetours(routeId, direction);

        if (response == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        CtaDetoursBustimeResponse bustimeResponse = response.bustimeResponse();

        if (bustimeResponse == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        List<CtaDetour> detours = bustimeResponse.dtrs();

        if ((detours == null) || detours.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return detours.stream()
                      .map(this.detourMapper::toDomainDetour)
                      .toList();
    }
}
