package com.dogsi.itil.controllers.configuration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dogsi.itil.dto.HardwareMetrics;
import com.dogsi.itil.dto.SlaMetrics;
import com.dogsi.itil.dto.SoftwareMetrics;
import com.dogsi.itil.services.configuration.ConfigurationMetrics;

@RestController
@RequestMapping("api/v1/configuration/metrics")
public class ConfigurationMetricsController {
    
    private ConfigurationMetrics service;

    public ConfigurationMetricsController(ConfigurationMetrics service) {
        this.service = service;
    }

    @GetMapping("/slas")
    public SlaMetrics getSlasMetrics(){
        return service.getSlaMetrics();
    }

    
    @GetMapping("/hardware")
    public HardwareMetrics getHardwareMetrics(){
        return service.getHardwareMetrics();
    }

    @GetMapping("/software")
    public SoftwareMetrics getSoftwareMetrics(){
        return service.getSoftwareMetrics();
    }
}
