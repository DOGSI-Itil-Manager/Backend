package com.dogsi.itil.controllers.problem;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dogsi.itil.domain.problem.Problem;
import com.dogsi.itil.dto.IdWithName;
import com.dogsi.itil.dto.ProblemDto;
import com.dogsi.itil.services.problem.ProblemService;

@RestController
@RequestMapping("api/v1/problem")
public class ProblemController {

    private ProblemService service;
    
    public ProblemController(ProblemService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Problem getProblemById(@PathVariable Long id){
        return service.getProblemById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addProblem(@RequestBody @Valid ProblemDto problemDto){
        service.saveProblem(problemDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProblem(@PathVariable Long id, @RequestBody @Valid ProblemDto problemDto){
        service.updateProblem(id, problemDto);
    }

    @GetMapping
    public Page<Problem> getProblem(Pageable pageable){
        return service.getProblem(pageable);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProblem(@PathVariable Long id){
        service.deleteProblem(id);
    }

    @GetMapping("/ids-with-names")
    public Page<IdWithName> getProblemIdsWithNames(Pageable pageable){
        return service.getProblemIdsWithNames(pageable);
    }
}
