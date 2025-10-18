package app.cta4j.train.controller;

import app.cta4j.train.dto.Train;
import app.cta4j.train.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trains")
public final class TrainController {
    private final TrainService trainService;

    @Autowired
    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @GetMapping("/{run}")
    public Train getTrain(@PathVariable String run) {
        return this.trainService.getLocation(run);
    }
}
