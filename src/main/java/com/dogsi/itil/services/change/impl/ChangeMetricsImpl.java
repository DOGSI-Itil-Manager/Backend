package com.dogsi.itil.services.change.impl;

import org.springframework.stereotype.Service;

import com.dogsi.itil.dto.ChangeMetricsDto;
import com.dogsi.itil.repositories.ChangeRepository;
import com.dogsi.itil.services.change.ChangeMetrics;

@Service
public class ChangeMetricsImpl implements ChangeMetrics {

    private ChangeRepository changeRepository;
    
    public ChangeMetricsImpl(ChangeRepository changeRepository) {
        this.changeRepository = changeRepository;
    }

    @Override
    public ChangeMetricsDto getChangeMetrics() {
        var totalChanges = changeRepository.count();
        var notTakenChanges = changeRepository.countChangesNotTaken();
        var incidentsInChanges = changeRepository.countIncidentsInChanges();
        var problemsInChanges = changeRepository.countProblemsInChanges();
        var cancelledChanges = changeRepository.countCancelledChanges();
        var changesByCategory = changeRepository.countChangeByCategory();
        var averageIncidents = totalChanges == 0 ? 0f :(float) incidentsInChanges / (float) totalChanges;
        var averageProblems = totalChanges == 0 ? 0f :(float) problemsInChanges / (float) totalChanges;
        return new ChangeMetricsDto(totalChanges, notTakenChanges, averageIncidents,averageProblems,cancelledChanges,changesByCategory);
    }
    
}
