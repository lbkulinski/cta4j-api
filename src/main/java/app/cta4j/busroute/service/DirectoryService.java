package app.cta4j.busroute.service;

import app.cta4j.busroute.dto.RouteDto;
import app.cta4j.busroute.dto.StopDto;
import app.cta4j.busroute.mapper.RouteMapper;
import app.cta4j.busroute.mapper.StopMapper;
import app.cta4j.busroute.repository.DirectionRepository;
import app.cta4j.busroute.repository.RouteRepository;
import app.cta4j.busroute.repository.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public final class DirectoryService {
    private final RouteRepository routeRepository;
    private final RouteMapper routeMapper;
    private final DirectionRepository directionRepository;
    private final StopRepository stopRepository;
    private final StopMapper stopMapper;

    @Autowired
    public DirectoryService(
        RouteRepository routeRepository,
        RouteMapper routeMapper,
        DirectionRepository directionRepository,
        StopRepository stopRepository,
        StopMapper stopMapper
    ) {
        this.routeRepository = routeRepository;
        this.routeMapper = routeMapper;
        this.directionRepository = directionRepository;
        this.stopRepository = stopRepository;
        this.stopMapper = stopMapper;
    }

    public List<RouteDto> getRoutes() {
        return this.routeRepository.findAll()
                                   .stream()
                                   .map(this.routeMapper::toDomain)
                                   .toList();
    }

    public List<String> getDirections(String routeId) {
        if (routeId == null) {
            throw new IllegalArgumentException("routeId must not be null");
        }

        if (!this.routeRepository.existsById(routeId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return this.directionRepository.findAllByRouteId(routeId);
    }

    public List<StopDto> getStops(String routeId, String direction) {
        if (routeId == null) {
            throw new IllegalArgumentException("routeId must not be null");
        }

        if (direction == null) {
            throw new IllegalArgumentException("direction must not be null");
        }

        if (!this.directionRepository.existsByRouteIdAndDirection(routeId, direction)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return this.stopRepository.findAllByRouteIdAndDirection(routeId, direction)
                                  .stream()
                                  .map(this.stopMapper::toDomain)
                                  .toList();
    }
}
