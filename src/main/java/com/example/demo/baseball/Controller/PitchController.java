package com.example.demo.baseball.Controller;

import com.example.demo.baseball.Model.PitchResult;
import com.example.demo.baseball.Service.PitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.baseball.Model.PitchRequest;

@RestController
@RequestMapping("/api")
public class PitchController {

    @Autowired
    private PitchService pitchService;

    @PostMapping("/pitch")
    public String pitch(@RequestBody PitchRequest request) {
        return pitchService.processPitch(request);
    }
    @GetMapping("/strategy")
    public String getStrategy(@RequestParam String batter) {
        return pitchService.getAttackStrategy(batter);
    }
}
