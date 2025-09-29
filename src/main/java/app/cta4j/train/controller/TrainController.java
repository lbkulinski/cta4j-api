package app.cta4j.train.controller;

import app.cta4j.train.dto.FollowTrainDto;
import app.cta4j.train.dto.TrainArrival;
import app.cta4j.train.dto.TrainStationDto;
import app.cta4j.train.service.TrainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@Tag(
    name = "Train API",
    description = "Provides access to train station data and upcoming train arrivals."
)
@RequestMapping("/api/trains")
@RestController
public final class TrainController {
    private final TrainService trainService;

    @Autowired
    public TrainController(TrainService trainService) {
        this.trainService = Objects.requireNonNull(trainService);
    }

    @Operation(
        summary = "Retrieve all train stations",
        description = "Returns a list of all train stations available in the system."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successful retrieval of train stations",
            content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = TrainStationDto.class))
            )
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = @Content()
        )
    })
    @GetMapping("/stations")
    public List<TrainStationDto> getStations() {
        return this.trainService.getStations();
    }

    @Operation(
        summary = "Retrieve upcoming train arrivals at a specific station",
        description = "Returns a list of upcoming train arrivals for the specified station ID."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successful retrieval of train arrivals",
            content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = TrainArrival.class))
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Station not found",
            content = @Content()
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = @Content()
        )
    })
    @GetMapping("/stations/{stationId}/arrivals")
    public List<TrainArrival> getArrivals(@PathVariable String stationId) {
        Objects.requireNonNull(stationId);

        return this.trainService.getArrivals(stationId);
    }

    @Operation(
        summary = "Retrieve upcoming arrivals for a train with a specific run number",
        description = "Returns the position and list of upcoming arrivals for the train with the specified run number."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successful retrieval of train position and upcoming arrivals",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = FollowTrainDto.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Run not found",
            content = @Content()
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = @Content()
        )
    })
    @GetMapping("/{run}/arrivals")
    public FollowTrainDto followTrain(@PathVariable @Positive int run) {
        return this.trainService.followTrain(run);
    }
}
