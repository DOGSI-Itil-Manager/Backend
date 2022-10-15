package com.dogsi.itil.services.configuration;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dogsi.itil.domain.configuration.SLA;
import com.dogsi.itil.dto.SlaDto;

public interface SlaService {
    void saveSla(SlaDto dto);

    Page<SLA> getSla(Pageable pageable);

    void updateSla(Long id, SlaDto dto);

    void deleteSla(Long id);
}
