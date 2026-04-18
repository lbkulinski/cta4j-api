package com.cta4j.api.train.controller;

import com.cta4j.api.train.dto.Train;
import com.cta4j.api.train.service.TrainService;
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
        return this.trainService.getTrain(run);
    }
}
