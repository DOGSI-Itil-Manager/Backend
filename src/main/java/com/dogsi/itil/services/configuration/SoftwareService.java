package com.dogsi.itil.services.configuration;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dogsi.itil.domain.configuration.Software;
import com.dogsi.itil.dto.SoftwareDto;

public interface SoftwareService {
    void saveSoftware(SoftwareDto software);

    Page<Software> getSoftware(Pageable pageable);

    void updateSoftware(Long id, SoftwareDto software);

    void deleteSoftware(Long id);
}
