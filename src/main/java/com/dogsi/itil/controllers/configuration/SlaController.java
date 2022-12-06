package com.dogsi.itil.controllers.configuration;

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

import com.dogsi.itil.domain.configuration.SLA;
import com.dogsi.itil.dto.IdWithName;
import com.dogsi.itil.dto.SlaDto;
import com.dogsi.itil.services.configuration.SlaService;

@RestController
@RequestMapping("api/v1/configuration/sla")
public class SlaController {
    
    private SlaService service;

    public SlaController(SlaService service) {
        this.service = service;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addSla( @RequestBody @Valid SlaDto dto){
        service.saveSla(dto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSla(@PathVariable Long id, @RequestBody @Valid SlaDto dto){
        service.updateSla(id, dto);
    }

    @GetMapping
    public Page<SLA> getSla(Pageable pageable){
        return service.getSla(pageable);
    }

    @GetMapping("/{id}")
    public SLA getSlaById(@PathVariable Long id){
        return service.getSlaById(id);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSla(@PathVariable Long id){
        service.deleteSla(id);
    }

    @GetMapping("/ids-with-names")
    public Page<IdWithName> getIdsWithNames(Pageable pageable){
        return service.getIdsWithNames(pageable);
    }
}
