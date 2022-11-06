package com.dogsi.itil.controllers.problem;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dogsi.itil.dto.ProblemMetricsDto;
import com.dogsi.itil.services.problem.ProblemMetricsService;

@RestController
@RequestMapping("api/v1/problem/metrics")
public class ProblemMetricsController {
    
    private ProblemMetricsService service;

    public ProblemMetricsController(ProblemMetricsService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ProblemMetricsDto getProblemMetrics(){
        return service.getProblemMetrics();
    }
}
