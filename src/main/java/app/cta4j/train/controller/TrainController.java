package app.cta4j.train.controller;

import app.cta4j.train.dto.TrainLocation;
import app.cta4j.train.service.TrainLocationService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trains")
public final class TrainController {
    private final TrainLocationService trainLocationService;

    @Autowired
    public TrainController(TrainLocationService trainLocationService) {
        this.trainLocationService = trainLocationService;
    }

    @GetMapping("/{run}/location")
    public TrainLocation getLocation(@PathVariable @Positive int run) {
        return this.trainLocationService.getLocation(run);
    }
}
