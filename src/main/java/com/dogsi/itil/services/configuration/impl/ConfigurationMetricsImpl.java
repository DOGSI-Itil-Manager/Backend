package com.dogsi.itil.services.configuration.impl;

import org.springframework.stereotype.Service;

import com.dogsi.itil.dto.HardwareMetrics;
import com.dogsi.itil.dto.SlaMetrics;
import com.dogsi.itil.dto.SoftwareMetrics;
import com.dogsi.itil.repositories.HardwareRepository;
import com.dogsi.itil.repositories.HardwareVersionsRepository;
import com.dogsi.itil.repositories.SlaRepository;
import com.dogsi.itil.repositories.SlaVersionsRepository;
import com.dogsi.itil.repositories.SoftwareVersionsRepository;
import com.dogsi.itil.repositories.SoftwareRepository;
import com.dogsi.itil.services.configuration.ConfigurationMetrics;

@Service
public class ConfigurationMetricsImpl implements ConfigurationMetrics{
    
    private HardwareRepository hardwareRepository;
    private SoftwareRepository softwareRepository;
    private SlaRepository slaRepository;
    private SlaVersionsRepository slaVersionsRepository;
    private HardwareVersionsRepository hardwareVersionsRepository;
    private SoftwareVersionsRepository softwareVersionsRepository;

    public ConfigurationMetricsImpl(HardwareRepository hardwareRepository,HardwareVersionsRepository hardwareVersionsRepository,
            SoftwareRepository softwareRepository, SoftwareVersionsRepository softwareVersionsRepository,
            SlaRepository slaRepository, SlaVersionsRepository slaVersionsRepository) {
        this.hardwareRepository = hardwareRepository;
        this.hardwareVersionsRepository = hardwareVersionsRepository;
        this.softwareRepository = softwareRepository;
        this.softwareVersionsRepository = softwareVersionsRepository;
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

    @Override
    public HardwareMetrics getHardwareMetrics() {
        var totalSpent = hardwareRepository.sumTotalSpentInHardware();
        totalSpent = totalSpent==null ? 0 :totalSpent;
        var hardwareByType = hardwareRepository.countHardwareByType();
        var totalHardware = hardwareRepository.count();
        var totalVersions = hardwareVersionsRepository.count();
        float changeSpeed = totalHardware != 0 ? (float) totalVersions / (float) totalHardware : 0f;

        return new HardwareMetrics(totalSpent,hardwareByType,totalHardware,changeSpeed);
    }

    @Override
    public SoftwareMetrics getSoftwareMetrics() {
        var softwareByType = softwareRepository.countSoftwareByType();
        var totalSoftware = softwareRepository.count();
        var totalVersions = softwareVersionsRepository.count();
        float changeSpeed = totalSoftware != 0 ? (float) totalVersions / (float) totalSoftware : 0f;
        return new SoftwareMetrics(softwareByType,totalSoftware,changeSpeed);
    }
}
