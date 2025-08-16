package app.cta4j.bus.controller;

import app.cta4j.bus.dto.BusArrivalDto;
import app.cta4j.bus.dto.BusDetourDto;
import app.cta4j.bus.dto.BusRouteDto;
import app.cta4j.bus.dto.BusStopDto;
import app.cta4j.bus.service.BusRouteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@Tag(
    name = "Bus Route API",
    description = "Endpoints for retrieving bus routes, stops, directions, detours, and arrival information."
)
@RequestMapping("/api/buses/routes")
@RestController
public final class BusRouteController {
    private final BusRouteService busRouteService;

    @Autowired
    public BusRouteController(BusRouteService busRouteService) {
        this.busRouteService = Objects.requireNonNull(busRouteService);
    }

    @Operation(
        summary = "Retrieve all bus routes",
        description = "Returns a list of all available bus routes in the system."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successful retrieval of bus routes",
            content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = BusRouteDto.class))
            )
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = @Content()
        )
    })
    @GetMapping
    public List<BusRouteDto> getRoutes() {
        return this.busRouteService.getRoutes();
    }

    @Operation(
        summary = "Retrieve directions for a specific bus route",
        description = """
        Returns the list of possible travel directions \
        (e.g., 'Northbound', 'Southbound') for the specified bus route."""
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successful retrieval of directions",
            content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = String.class))
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Route not found",
            content = @Content()
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = @Content()
        )
    })
    @GetMapping("/{routeId}/directions")
    public List<String> getDirections(@PathVariable String routeId) {
        Objects.requireNonNull(routeId);

        return this.busRouteService.getDirections(routeId);
    }

    @Operation(
        summary = "Retrieve stops for a specific bus route and direction",
        description = "Returns a list of bus stops for the specified route and travel direction."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successful retrieval of bus stops",
            content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = BusStopDto.class))
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Bus stops not found for the specified route and direction",
            content = @Content()
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = @Content()
        )
    })
    @GetMapping("/{routeId}/directions/{direction}/stops")
    public List<BusStopDto> getStops(@PathVariable String routeId, @PathVariable String direction) {
        Objects.requireNonNull(routeId);

        Objects.requireNonNull(direction);

        return this.busRouteService.getStops(routeId, direction);
    }

    @Operation(
        summary = "Retrieve active detours for a specific bus route and direction",
        description = "Returns a list of active detours affecting the specified bus route and travel direction."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successful retrieval of detours",
            content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = BusDetourDto.class))
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Detours not found for the specified route and direction",
            content = @Content()
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = @Content()
        )
    })
    @GetMapping("/{routeId}/directions/{direction}/detours")
    public List<BusDetourDto> getDetours(@PathVariable String routeId, @PathVariable String direction) {
        Objects.requireNonNull(routeId);

        Objects.requireNonNull(direction);

        return this.busRouteService.getDetours(routeId, direction);
    }

    @Operation(
        summary = "Retrieve upcoming bus arrivals at a specific stop",
        description = "Returns a list of upcoming bus arrivals for the specified route and stop."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successful retrieval of bus arrivals",
            content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = BusArrivalDto.class))
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Bus arrivals not found for the specified route and stop",
            content = @Content()
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = @Content()
        )
    })
    @GetMapping("/{routeId}/stops/{stopId}/arrivals")
    public List<BusArrivalDto> getArrivals(@PathVariable String routeId, @PathVariable String stopId) {
        Objects.requireNonNull(routeId);

        Objects.requireNonNull(stopId);

        return this.busRouteService.getArrivals(routeId, stopId);
    }
}
