package com.dogsi.itil.services.knownError.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dogsi.itil.domain.knownError.solution.Solution;
import com.dogsi.itil.dto.IdWithName;
import com.dogsi.itil.dto.SolutionDto;
import com.dogsi.itil.exceptions.ItemNotFoundException;
import com.dogsi.itil.repositories.SolutionRepository;
import com.dogsi.itil.repositories.KnownErrorRepository;
import com.dogsi.itil.services.knownError.SolutionService;
import com.dogsi.itil.domain.State;
import com.dogsi.itil.domain.knownError.KnownError;

@Service
public class SolutionServiceImpl implements SolutionService {

    private SolutionRepository repository;
    private KnownErrorRepository knownErrorRepository;

    public SolutionServiceImpl(SolutionRepository repository, KnownErrorRepository knownErrorRepository) {
        this.repository = repository;
        this.knownErrorRepository = knownErrorRepository;
    }

    @Override
    public void saveSolution(SolutionDto dto) {
        var solution = Solution.builder()
            .name(dto.getName())
            .creationDate(dto.getCreationDate())
            .description(dto.getDescription())
            .build();        
        
        repository.save(solution);
    }

    @Override
    public Page<Solution> getSolution(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void updateSolution(Long id, SolutionDto dto) {

        var solution = repository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Solution with id " + id + " not found");
        });

        solution.setName(dto.getName());
        solution.setCreationDate(dto.getCreationDate());

        repository.save(solution);
    }

    @Override
    public void deleteSolution(Long id) {
        int deleted = repository.deleteSolutionById(id);
        if (deleted == 0) {
            throw new ItemNotFoundException("Solution with id " + id + " not found");
        }
    }

    @Override
    public Solution getSolutionById(Long id) {
        return repository.findById(id).orElseThrow(() -> {throw new ItemNotFoundException("Solution with id " + id + " not found");});
    }

    @Override
    public Page<IdWithName> getIdsWithNames(Pageable pageable) {
        return repository.getIdsAndNames(pageable);
    }

}