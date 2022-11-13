package com.dogsi.itil.services.change;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dogsi.itil.domain.changes.Change;
import com.dogsi.itil.dto.ChangeDto;

public interface ChangeService {
    void saveChange(ChangeDto problem);

    Page<Change> getChange(Pageable pageable);

    void updateChange(Long id, ChangeDto problem);

    void deleteChange(Long id);

    Change getChangeById(Long id);
}
