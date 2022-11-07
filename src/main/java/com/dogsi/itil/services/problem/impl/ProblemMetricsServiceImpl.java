package com.dogsi.itil.services.problem.impl;

import org.springframework.stereotype.Service;

import com.dogsi.itil.dto.ProblemMetricsDto;
import com.dogsi.itil.repositories.ProblemRepository;
import com.dogsi.itil.services.problem.ProblemMetricsService;

@Service
public class ProblemMetricsServiceImpl implements ProblemMetricsService {
    
    private ProblemRepository problemRepository;

    public ProblemMetricsServiceImpl(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    @Override
    public ProblemMetricsDto getProblemMetrics() {
        var countByUserInCharge = problemRepository.countProblemsByUserInCharge();
        return new ProblemMetricsDto(countByUserInCharge);
    }
}
