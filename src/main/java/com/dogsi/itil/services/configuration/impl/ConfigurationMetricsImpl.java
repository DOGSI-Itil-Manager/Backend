package com.dogsi.itil.services.configuration.impl;

import org.springframework.stereotype.Service;

import com.dogsi.itil.dto.SlaMetrics;
import com.dogsi.itil.repositories.HardwareRepository;
import com.dogsi.itil.repositories.SlaRepository;
import com.dogsi.itil.repositories.SlaVersionsRepository;
import com.dogsi.itil.repositories.SoftwareRepository;
import com.dogsi.itil.services.configuration.ConfigurationMetrics;

@Service
public class ConfigurationMetricsImpl implements ConfigurationMetrics{
    
    private HardwareRepository hardwareRepository;
    private SoftwareRepository softwareRepository;
    private SlaRepository slaRepository;
    private SlaVersionsRepository slaVersionsRepository;

    public ConfigurationMetricsImpl(HardwareRepository hardwareRepository, SoftwareRepository softwareRepository,
            SlaRepository slaRepository, SlaVersionsRepository slaVersionsRepository) {
        this.hardwareRepository = hardwareRepository;
        this.softwareRepository = softwareRepository;
        this.slaRepository = slaRepository;
        this.slaVersionsRepository = slaVersionsRepository;
    }

    @Override
    public SlaMetrics getSlaMetrics() {
        var crucialSlas = slaRepository.countCrucialSlas();
        var countByManager = slaRepository.countSlasByManager();
        var countByClient = slaRepository.countSlasByClient();
        var countByProvider = slaRepository.countSlasByProvider();
        var countByService = slaRepository.countSlasByService();
        var totalSlas = slaRepository.count();
        var totalVersions = slaVersionsRepository.count();
        float changeSpeed = totalSlas != 0 ? (float) totalVersions / (float) totalSlas : 0f;
        return new SlaMetrics(countByManager,countByClient,countByProvider,countByService,crucialSlas,totalSlas,changeSpeed);
    }
}
