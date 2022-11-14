package com.dogsi.itil.services.problem.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dogsi.itil.domain.problem.Problem;
import com.dogsi.itil.dto.IdWithName;
import com.dogsi.itil.dto.ProblemDto;
import com.dogsi.itil.exceptions.ItemNotFoundException;
import com.dogsi.itil.repositories.ProblemRepository;
import com.dogsi.itil.repositories.IncidentRepository;
import com.dogsi.itil.services.problem.ProblemService;


@Service
public class ProblemServiceImpl implements ProblemService {

    private ProblemRepository repository;
    private IncidentRepository incidentRepository;

    public ProblemServiceImpl(ProblemRepository repository, IncidentRepository incidentRepository) {
        this.repository = repository;
        this.incidentRepository = incidentRepository;
    }

    @Override
    public void saveProblem(ProblemDto dto) {
        var problem = Problem.builder()
                .name(dto.getName())
                .category(dto.getCategory())
                .priority(dto.getPriority())
                .impact(dto.getImpact())
                .state(dto.getState())
                .description(dto.getDescription())
                .reportedDate(dto.getReportedDate())
                .closedDate(dto.getClosedDate())
                .emailOfUserInCharge(dto.getEmailOfUserInCharge())
                .build();
        var ids = dto.getIncidents();
        if(ids!=null && !ids.isEmpty()){
            var incidents = incidentRepository.findAllById(ids);
            if(incidents.size() != ids.size()) {
                throw new ItemNotFoundException("Incident not found");
            }
            problem.addIncidents(incidents);
        }

        repository.save(problem);
    }

    @Override
    public Page<Problem> getProblem(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void updateProblem(Long id, ProblemDto dto) {
        var problem = repository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Problem with id " + id + " not found");
        });

        var ids = dto.getIncidents();
        if(ids!=null && !ids.isEmpty()){
            var incidents = incidentRepository.findAllById(dto.getIncidents());
            if(incidents.size() != dto.getIncidents().size()) {
                throw new ItemNotFoundException("Incident not found");
            }
            problem.addIncidents(incidents);
        }
        
        problem.setName(dto.getName());
        problem.setCategory(dto.getCategory());
        problem.setPriority(dto.getPriority());
        problem.setImpact(dto.getImpact());
        problem.setState(dto.getState());
        problem.setDescription(dto.getDescription());
        problem.setReportedDate(dto.getReportedDate());
        problem.setClosedDate(dto.getClosedDate());
        problem.setEmailOfUserInCharge(dto.getEmailOfUserInCharge());

        repository.save(problem);

    }

    @Override
    public void deleteProblem(Long id) {
        int deleted = repository.deleteProblemById(id);
        if (deleted == 0) {
            throw new ItemNotFoundException("Problem with id " + id + " not found");
        }
    }

    @Override
    public Problem getProblemById(Long id) {
        return repository.findById(id).orElseThrow(() -> {throw new ItemNotFoundException("Problem with id " + id + " not found");});
    }

    @Override
    public Page<IdWithName> getProblemIdsWithNames(Pageable pageable) {
        return repository.getIdsAndNamesOfProblems(pageable);
    }

}