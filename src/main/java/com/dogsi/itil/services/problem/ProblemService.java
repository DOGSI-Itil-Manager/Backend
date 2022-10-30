package com.dogsi.itil.services.problem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dogsi.itil.domain.problem.Problem;
import com.dogsi.itil.dto.ProblemDto;
import com.dogsi.itil.dto.ProblemIncidentDto;

public interface ProblemService {

    void saveProblem(ProblemDto problem);

    Page<Problem> getProblem(Pageable pageable);

    void updateProblem(Long id, ProblemDto problem);

    void addIncident(Long id, ProblemIncidentDto piDto);

    void deleteProblem(Long id);

    public Problem getProblemById(Long id);
}
