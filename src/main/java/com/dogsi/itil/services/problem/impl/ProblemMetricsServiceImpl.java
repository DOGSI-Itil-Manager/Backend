package com.dogsi.itil.services.problem.impl;

import org.springframework.stereotype.Service;

import com.dogsi.itil.dto.ProblemMetricsDto;
import com.dogsi.itil.repositories.ProblemRepository;
import com.dogsi.itil.services.problem.ProblemMetricsService;

@Service
public class ProblemMetricsServiceImpl implements ProblemMetricsService {
    
    private ProblemRepository repository;

    public ProblemMetricsServiceImpl(ProblemRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProblemMetricsDto getProblemMetrics() {
        var countByUserInCharge = repository.countProblemsByUserInCharge();
        var byPriority = repository.countProblemByPriority();
        var byDay = repository.countProblemByDay();
        var byCategory = repository.countProblemByCategory();
        return new ProblemMetricsDto(countByUserInCharge, byPriority, byDay, byCategory);
    }
}
