package app.cta4j.bus.service;

import app.cta4j.bus.client.BusApiClient;
import app.cta4j.bus.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;

import java.util.List;
import java.util.Objects;

@Service
public class BusRouteService {
    private final BusApiClient busApiClient;

    @Autowired
    public BusRouteService(Environment env, DynamoDbEnhancedClient dynamoDbClient, BusApiClient busApiClient) {


        this.busApiClient = Objects.requireNonNull(busApiClient);
    }

    public List<BusDetourDto> getDetours(String routeId, String direction) {
        Objects.requireNonNull(routeId);

        Objects.requireNonNull(direction);

        BusDetourResponseDto response = this.busApiClient.getDetours(routeId, direction);

        if (response == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        BusDetourBodyDto body = response.body();

        if (body == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        List<BusDetourDto> detours = body.detours();

        if ((detours == null) || detours.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return List.copyOf(detours);
    }

    public List<BusArrivalDto> getArrivals(String routeId, String stopId) {
        Objects.requireNonNull(routeId);

        Objects.requireNonNull(stopId);

        BusArrivalResponseDto response = this.busApiClient.getArrivals(routeId, stopId);

        if (response == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        BusArrivalBodyDto body = response.body();

        if (body == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        List<BusArrivalDto> arrivals = body.arrivals();

        if ((arrivals == null) || arrivals.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return List.copyOf(arrivals);
    }
}
