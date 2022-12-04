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

import com.dogsi.itil.domain.knownError.KnownError;
import com.dogsi.itil.dto.IdWithName;
import com.dogsi.itil.dto.KnownErrorDto;
import com.dogsi.itil.services.knownError.KnownErrorService;

@RestController
@RequestMapping("api/v1/knownError")
public class KnownErrorController {

    private KnownErrorService service;
    
    public KnownErrorController(KnownErrorService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public KnownError getKnownErrorById(@PathVariable Long id){
        return service.getKnownErrorById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addKnownError(@RequestBody @Valid KnownErrorDto knownErrorDto){
        service.saveKnownError(knownErrorDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateKnownError(@PathVariable Long id, @RequestBody @Valid KnownErrorDto knownErrorDto){
        service.updateKnownError(id, knownErrorDto);
    }

    @GetMapping
    public Page<KnownError> getKnownError(Pageable pageable){
        return service.getKnownError(pageable);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteKnownError(@PathVariable Long id){
        service.deleteKnownError(id);
    }

    @GetMapping("/ids-with-names")
    public Page<IdWithName> getIdsWithNames(Pageable pageable){
        return service.getIdsWithNames(pageable);
    }
}
