package com.dogsi.itil.services.change.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dogsi.itil.domain.changes.Change;

import com.dogsi.itil.dto.ChangeDto;
import com.dogsi.itil.exceptions.ItemNotFoundException;
import com.dogsi.itil.repositories.ChangeRepository;
import com.dogsi.itil.repositories.IncidentRepository;
import com.dogsi.itil.repositories.ProblemRepository;
import com.dogsi.itil.services.change.ChangeService;

@Service
public class ChangeServiceImpl implements ChangeService {

    private IncidentRepository incidentRepository;
    private ProblemRepository problemRepository;
    private ChangeRepository changeRepository;

    public ChangeServiceImpl(IncidentRepository incidentRepository, ProblemRepository problemRepository,
            ChangeRepository changeRepository) {
        this.incidentRepository = incidentRepository;
        this.problemRepository = problemRepository;
        this.changeRepository = changeRepository;
    }

    @Override
    public void saveChange(ChangeDto dto) {

        var change = Change.builder()
            .name(dto.getName())
            .category(dto.getCategory())
            .closedDate(dto.getClosedDate())
            .description(dto.getDescription())
            .emailOfUserInCharge(dto.getEmailOfUserInCharge())
            .impact(dto.getImpact())
            .state(dto.getState())
            .priority(dto.getPriority())
            .build();

        var incidentIds = dto.getIncidents();
        if(incidentIds!=null && !incidentIds.isEmpty()){
            var incidents = incidentRepository.findAllById(incidentIds);
            if(incidents.size() != incidentIds.size()) {
                throw new ItemNotFoundException("Incident not found");
            }
            change.addIncidents(incidents);
        }

        var problemIds = dto.getProblems();
        if(problemIds!=null && !problemIds.isEmpty()){
            var problems = problemRepository.findAllById(problemIds);
            if(problems.size() != problemIds.size()) {
                throw new ItemNotFoundException("Problem not found");
            }
            change.addProblems(problems);
        }

        changeRepository.save(change);

    }

    @Override
    public Page<Change> getChange(Pageable pageable) {
        return changeRepository.findAll(pageable);
    }

    @Override
    public void updateChange(Long id, ChangeDto dto) {
        changeRepository.deleteIncidentRelationships(id);
        changeRepository.deleteProblemRelationships(id);

        var change = changeRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Change with id " + id + " not found");
        });

        var incidentIds = dto.getIncidents();
        if(incidentIds!=null && !incidentIds.isEmpty()){
            var incidents = incidentRepository.findAllById(incidentIds);
            if(incidents.size() != incidentIds.size()) {
                throw new ItemNotFoundException("Incident not found");
            }
            change.addIncidents(incidents);
        }

        var problemIds = dto.getProblems();
        if(problemIds!=null && !problemIds.isEmpty()){
            var problems = problemRepository.findAllById(problemIds);
            if(problems.size() != problemIds.size()) {
                throw new ItemNotFoundException("Problem not found");
            }
            change.addProblems(problems);
        }

        change.setName(dto.getName());
        change.setCategory(dto.getCategory());
        change.setClosedDate(dto.getClosedDate());
        change.setDescription(dto.getDescription());
        change.setEmailOfUserInCharge(dto.getEmailOfUserInCharge());
        change.setImpact(dto.getImpact());
        change.setState(dto.getState());
        change.setPriority(dto.getPriority());

        changeRepository.save(change);
    }

    @Override
    public void deleteChange(Long id) {
        int deleted = changeRepository.deleteChangeById(id);
        if (deleted == 0) {
            throw new ItemNotFoundException("Change with id " + id + " not found");
        }
    }

    @Override
    public Change getChangeById(Long id) {
        var change = changeRepository.findById(id).orElseThrow(() -> {throw new ItemNotFoundException("Change with id " + id + " not found");});
        // change.getIncidents();
        // change.getProblems();
        return change;
    }
    
}
