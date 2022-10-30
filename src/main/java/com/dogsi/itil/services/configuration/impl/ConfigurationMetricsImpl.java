package com.dogsi.itil.services.configuration.impl;

import org.springframework.stereotype.Service;

import com.dogsi.itil.dto.SlaMetrics;
import com.dogsi.itil.repositories.HardwareRepository;
import com.dogsi.itil.repositories.SlaRepository;
import com.dogsi.itil.repositories.SoftwareRepository;
import com.dogsi.itil.services.configuration.ConfigurationMetrics;

@Service
public class ConfigurationMetricsImpl implements ConfigurationMetrics{
    
    private HardwareRepository hardwareRepository;
    private SoftwareRepository softwareRepository;
    private SlaRepository slaRepository;
    
    public ConfigurationMetricsImpl(HardwareRepository hardwareRepository, SoftwareRepository softwareRepository,
            SlaRepository slaRepository) {
        this.hardwareRepository = hardwareRepository;
        this.softwareRepository = softwareRepository;
        this.slaRepository = slaRepository;
    }

    @Override
    public SlaMetrics getSlaMetrics() {
        var crucialSlas = slaRepository.countCrucialSlas();
        var countByManager = slaRepository.countSlasByManager();
        var countByClient = slaRepository.countSlasByClient();
        var countByService = slaRepository.countSlasByService();
        return new SlaMetrics(countByManager,countByClient,countByService,crucialSlas);
    }
}
