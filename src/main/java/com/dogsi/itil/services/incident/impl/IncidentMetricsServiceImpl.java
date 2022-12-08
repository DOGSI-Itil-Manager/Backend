package com.dogsi.itil.services.incident.impl;

import org.springframework.stereotype.Service;

import com.dogsi.itil.dto.IncidentMetricsDto;
import com.dogsi.itil.repositories.IncidentRepository;
import com.dogsi.itil.services.incident.IncidentMetricsService;

@Service
public class IncidentMetricsServiceImpl implements IncidentMetricsService{

    private IncidentRepository repository;

    public IncidentMetricsServiceImpl(IncidentRepository repository) {
        this.repository = repository;
    }


    @Override
    public IncidentMetricsDto getMetrics() {
        var total = repository.count();
        var notTaken = repository.countIncidentsNotTaken();
        var byPriority = repository.countIncidentByPriority();
        var byDay = repository.countIncidentByDay();
        var bySatisfaction = repository.countIncidentBySatisfaction();
        var byCategory = repository.countIncidentByCategory();
        var totalHardware = (float) repository.countTotalHardware();
        var totalSla = (float) repository.countTotalSLa();
        var totalSoftware = (float) repository.countTotalSoftware();
        var lifetime = repository.averageLifetime();
        var averageHardware = total == 0 ? 0f : totalHardware / total;
        var averageSla = total == 0 ? 0f : totalSla / total;
        var averageSoftware = total == 0 ? 0f : totalSoftware / total;

        return new IncidentMetricsDto(total,notTaken,byPriority,byDay,bySatisfaction,byCategory,averageHardware,averageSla,averageSoftware,lifetime);
    }
    
}
