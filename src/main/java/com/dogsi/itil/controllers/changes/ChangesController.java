package com.dogsi.itil.controllers.changes;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.dogsi.itil.domain.changes.Change;
import com.dogsi.itil.dto.ChangeDto;
import com.dogsi.itil.dto.ChangeMetricsDto;
import com.dogsi.itil.services.change.ChangeMetrics;
import com.dogsi.itil.services.change.ChangeService;

@RestController
@RequestMapping("api/v1/changes")
public class ChangesController {
    
    private ChangeService service;
    private ChangeMetrics metrics;
    
    public ChangesController(ChangeService service, ChangeMetrics metrics) {
        this.service = service;
        this.metrics = metrics;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateChange(@PathVariable Long id, @RequestBody @Valid ChangeDto changeDto){
        service.updateChange(id, changeDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Change getChange(@PathVariable Long id){
        return service.getChangeById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addChange( @RequestBody @Valid ChangeDto changeDto){
        service.saveChange(changeDto);
    }

    @GetMapping
    public Page<Change> getChanges(Pageable pageable){
        return service.getChange(pageable);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteChange(@PathVariable Long id){
        service.deleteChange(id);
    }

    @GetMapping("/metrics")
    public ChangeMetricsDto getMetrics(){
        return metrics.getChangeMetrics();
    }

}
