package com.dogsi.itil.services.problem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dogsi.itil.domain.problem.Problem;
import com.dogsi.itil.dto.IdWithName;
import com.dogsi.itil.dto.ProblemDto;

public interface ProblemService {

    void saveProblem(ProblemDto problem);

    Page<Problem> getProblem(Pageable pageable);

    void updateProblem(Long id, ProblemDto problem);

    void deleteProblem(Long id);

    Problem getProblemById(Long id);

    Page<IdWithName> getProblemIdsWithNames(Pageable pageable);
}
