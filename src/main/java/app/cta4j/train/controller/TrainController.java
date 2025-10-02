package app.cta4j.train.controller;

import app.cta4j.train.dto.TrainLocation;
import app.cta4j.train.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
    name = "Train API",
    description = "Provides access to train station data and upcoming train arrivals."
)
@RequestMapping("/api/trains")
@RequiredArgsConstructor
@RestController
public final class TrainController {
    private final LocationService locationService;

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
                schema = @Schema(implementation = TrainLocation.class)
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
    @GetMapping("/{run}/location")
    public TrainLocation getLocation(@PathVariable @Positive int run) {
        return this.locationService.getLocation(run);
    }
}
