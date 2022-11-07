package com.dogsi.itil.services.incident;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dogsi.itil.domain.incident.Incident;
import com.dogsi.itil.dto.IdWithNamesOfIncident;
import com.dogsi.itil.dto.IncidentDto;

public interface IncidentService {

    void saveIncident(IncidentDto incident);

    Page<Incident> getIncident(Pageable pageable);

    void updateIncident(Long id, IncidentDto incident);

    void deleteIncident(Long id);

    public Incident getIncidentById(Long id);

    Page<IdWithNamesOfIncident> getIncidentIdsWithNames(Pageable pageable);
}
