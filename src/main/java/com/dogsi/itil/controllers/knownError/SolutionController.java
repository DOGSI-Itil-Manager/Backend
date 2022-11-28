package com.dogsi.itil.controllers.knownError;

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

import com.dogsi.itil.domain.knownError.solution.Solution;
import com.dogsi.itil.dto.SolutionDto;
import com.dogsi.itil.services.knownError.SolutionService;

@RestController
@RequestMapping("api/v1/solution")
public class SolutionController {

    private SolutionService service;
    
    public SolutionController(SolutionService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Solution getSolutionById(@PathVariable Long id){
        return service.getSolutionById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addSolution(@RequestBody @Valid SolutionDto solutionDto){
        service.saveSolution(solutionDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSolution(@PathVariable Long id, @RequestBody @Valid SolutionDto solutionDto){
        service.updateSolution(id, solutionDto);
    }

    @GetMapping
    public Page<Solution> getSolution(Pageable pageable){
        return service.getSolution(pageable);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSolution(@PathVariable Long id){
        service.deleteSolution(id);
    }
}
