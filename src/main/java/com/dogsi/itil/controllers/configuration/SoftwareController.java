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


import com.dogsi.itil.domain.configuration.Software;
import com.dogsi.itil.dto.SoftwareDto;
import com.dogsi.itil.services.configuration.SoftwareService;

@RestController
@RequestMapping("api/v1/configuration/software")
public class SoftwareController {
    
    
    private SoftwareService service;
    
    public SoftwareController(SoftwareService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addSoftware( @RequestBody @Valid SoftwareDto dto){
        service.saveSoftware(dto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSoftware(@PathVariable Long id, @RequestBody @Valid SoftwareDto dto){
        service.updateSoftware(id, dto);
    }

    @GetMapping
    public Page<Software> getSoftware(Pageable pageable){
        return service.getSoftware(pageable);
    }

    @GetMapping("/{id}")
    public Software getSoftwareById(@PathVariable Long id){
        return service.getSoftwareById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSoftware(@PathVariable Long id){
        service.deleteSoftware(id);
    }
}
