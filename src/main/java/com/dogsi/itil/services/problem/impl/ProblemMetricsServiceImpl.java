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
        var totalProblems = repository.count();
        var countByUserInCharge = repository.countProblemsByUserInCharge();
        var byPriority = repository.countProblemByPriority();
        var byDay = repository.countProblemByDay();
        var byCategory = repository.countProblemByCategory();
        var totalIncidentsInProblems = (float)repository.countIncidentsInProblems();
        var incidentsPerProblem = totalIncidentsInProblems / totalProblems;
        return new ProblemMetricsDto(totalProblems, countByUserInCharge, byPriority, byDay, byCategory,incidentsPerProblem);
    }
}
