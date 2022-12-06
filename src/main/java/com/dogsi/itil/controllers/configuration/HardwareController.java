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

import com.dogsi.itil.domain.configuration.Hardware;
import com.dogsi.itil.dto.HardwareDto;
import com.dogsi.itil.dto.IdWithName;
import com.dogsi.itil.services.configuration.HardwareService;

@RestController
@RequestMapping("api/v1/configuration/hardware")
public class HardwareController {
    
    
    private HardwareService service;
    
    public HardwareController(HardwareService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addHardware( @RequestBody @Valid HardwareDto hardwareDto){
        service.saveHardware(hardwareDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateHardware(@PathVariable Long id, @RequestBody @Valid HardwareDto hardwareDto){
        service.updateHardware(id, hardwareDto);
    }

    @GetMapping
    public Page<Hardware> getHardware(Pageable pageable){
        return service.getHardware(pageable);
    }

    @GetMapping("/{id}")
    public Hardware getHardwareById(@PathVariable Long id){
        return service.getHardwareById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHardware(@PathVariable Long id){
        service.deleteHardware(id);
    }

    @GetMapping("/ids-with-names")
    public Page<IdWithName> getIdsWithNames(Pageable pageable){
        return service.getIdsWithNames(pageable);
    }
}
