package com.dogsi.itil.services.knownError;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dogsi.itil.domain.knownError.KnownError;
import com.dogsi.itil.dto.IdWithName;
import com.dogsi.itil.dto.KnownErrorDto;

public interface KnownErrorService {

    void saveKnownError(KnownErrorDto knownError);

    Page<KnownError> getKnownError(Pageable pageable);

    void updateKnownError(Long id, KnownErrorDto knownError);

    void deleteKnownError(Long id);

    public KnownError getKnownErrorById(Long id);

    Page<IdWithName> getIdsWithNames(Pageable pageable);
}
