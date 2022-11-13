package com.dogsi.itil.services.incident.impl;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dogsi.itil.domain.incident.Incident;
import com.dogsi.itil.dto.IdWithName;
import com.dogsi.itil.dto.IncidentDto;
import com.dogsi.itil.exceptions.ItemNotFoundException;
import com.dogsi.itil.repositories.IncidentRepository;
import com.dogsi.itil.services.incident.IncidentService;

@Service
public class IncidentServiceImpl implements IncidentService {

    private IncidentRepository repository;

    public IncidentServiceImpl(IncidentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveIncident(IncidentDto dto) {
        var incident = Incident.builder()
                .name(dto.getName())
                .category(dto.getCategory())
                .priority(dto.getPriority())
                .impact(dto.getImpact())
                .state(dto.getState())
                .assignee(dto.getAssignee())
                .description(dto.getDescription())
                .reportedDate(dto.getReportedDate())
                .closedDate(dto.getClosedDate())
                .build();
        repository.save(incident);
    }

    @Override
    public Page<Incident> getIncident(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void updateIncident(Long id, IncidentDto dto) {
        var incident = repository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Incident with id " + id + " not found");
        });
        incident.setName(dto.getName());
        incident.setCategory(dto.getCategory());
        incident.setPriority(dto.getPriority());
        incident.setImpact(dto.getImpact());
        incident.setState(dto.getState());
        incident.setAssignee(dto.getAssignee());
        incident.setDescription(dto.getDescription());
        incident.setReportedDate(dto.getReportedDate());
        incident.setClosedDate(dto.getClosedDate());

        repository.save(incident);
    }

    @Override
    public void deleteIncident(Long id) {
        int deleted = repository.deleteIncidentById(id);
        if (deleted == 0) {
            throw new ItemNotFoundException("Incident with id " + id + " not found");
        }
    }

    public Incident getIncidentById(Long id) {
        return repository.findById(id).orElseThrow(() -> {throw new ItemNotFoundException("Incident with id " + id + " not found");});
    }

    @Override
    public Page<IdWithName> getIncidentIdsWithNames(Pageable pageable) {
        return repository.getIdsAndNamesOfIncidents(pageable);
    }
}
