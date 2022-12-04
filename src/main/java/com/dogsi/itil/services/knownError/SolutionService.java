package com.dogsi.itil.services.knownError;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dogsi.itil.domain.knownError.solution.Solution;
import com.dogsi.itil.dto.IdWithName;
import com.dogsi.itil.dto.SolutionDto;
import com.dogsi.itil.dto.SolutionsResponseDto;

public interface SolutionService {

    void saveSolution(SolutionDto knownError);

    Page<Solution> getSolution(Pageable pageable);

    void updateSolution(Long id, SolutionDto knownError);

    void deleteSolution(Long id);

    public SolutionsResponseDto getSolutionById(Long id);

    Page<IdWithName> getIdsWithNames(Pageable pageable);
}
