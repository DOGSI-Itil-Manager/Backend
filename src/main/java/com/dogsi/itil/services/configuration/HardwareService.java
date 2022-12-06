package com.dogsi.itil.services.configuration;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dogsi.itil.domain.configuration.Hardware;
import com.dogsi.itil.dto.HardwareDto;
import com.dogsi.itil.dto.IdWithName;

public interface HardwareService {
    
    void saveHardware(HardwareDto hardware);

    Page<Hardware> getHardware(Pageable pageable);

    void updateHardware(Long id, HardwareDto hardware);

    void deleteHardware(Long id);

    Hardware getHardwareById(Long id);

    Page<IdWithName> getIdsWithNames(Pageable pageable);
}
