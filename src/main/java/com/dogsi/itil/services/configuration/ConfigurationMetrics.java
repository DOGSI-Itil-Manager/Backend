package com.dogsi.itil.services.configuration;

import com.dogsi.itil.dto.HardwareMetrics;
import com.dogsi.itil.dto.SlaMetrics;
import com.dogsi.itil.dto.SoftwareMetrics;

public interface ConfigurationMetrics {
    
    SlaMetrics getSlaMetrics();

    HardwareMetrics getHardwareMetrics();

    SoftwareMetrics getSoftwareMetrics();


}
