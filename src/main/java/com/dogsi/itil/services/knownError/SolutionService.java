package com.dogsi.itil.services.knownError;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dogsi.itil.domain.knownError.solution.Solution;
import com.dogsi.itil.dto.SolutionDto;

public interface SolutionService {

    void saveSolution(SolutionDto solution);

    Page<Solution> getSolution(Pageable pageable);

    void updateSolution(Long id, SolutionDto solution);

    void deleteSolution(Long id);

    public Solution getSolutionById(Long id);
}
