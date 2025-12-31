package app.cta4j.busroute.service;

import app.cta4j.busroute.dto.RouteDto;
import app.cta4j.busroute.dto.StopDto;
import app.cta4j.busroute.repository.DirectionRepository;
import app.cta4j.busroute.repository.RouteRepository;
import app.cta4j.busroute.repository.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public final class DirectoryService {
    private final RouteRepository routeRepository;
    private final DirectionRepository directionRepository;
    private final StopRepository stopRepository;

    @Autowired
    public DirectoryService(
        RouteRepository routeRepository,
        DirectionRepository directionRepository,
        StopRepository stopRepository
    ) {
        this.routeRepository = routeRepository;
        this.directionRepository = directionRepository;
        this.stopRepository = stopRepository;
    }

    public List<RouteDto> getRoutes() {
        List<RouteDto> routes = this.routeRepository.findAll();

        return List.copyOf(routes);
    }

    public List<String> getDirections(String routeId) {
        if (routeId == null) {
            throw new IllegalArgumentException("routeId must not be null");
        }

        Optional<List<String>> optionalDirections = this.directionRepository.findAllByRouteId(routeId);

        if (optionalDirections.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        List<String> directions = optionalDirections.get();

        return List.copyOf(directions);
    }

    public List<StopDto> getStops(String routeId, String direction) {
        if (routeId == null) {
            throw new IllegalArgumentException("routeId must not be null");
        }

        if (direction == null) {
            throw new IllegalArgumentException("direction must not be null");
        }

        Optional<List<StopDto>> optionalStops = this.stopRepository.findAllByRouteIdAndDirection(routeId, direction);

        if (optionalStops.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        List<StopDto> stops = optionalStops.get();

        return List.copyOf(stops);
    }
}
