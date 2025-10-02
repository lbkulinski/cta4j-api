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

@RestController
@RequestMapping("/api/trains")
@RequiredArgsConstructor
public final class TrainController {
    private final LocationService locationService;

    @GetMapping("/{run}/location")
    public TrainLocation getLocation(@PathVariable @Positive int run) {
        return this.locationService.getLocation(run);
    }
}
