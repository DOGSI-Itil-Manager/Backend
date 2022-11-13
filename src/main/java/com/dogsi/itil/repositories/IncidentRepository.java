package com.dogsi.itil.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.dogsi.itil.domain.incident.Incident;
import com.dogsi.itil.dto.IdWithName;

public interface IncidentRepository extends JpaRepository<Incident, Long>{
    
    @Transactional
    @Modifying
    @Query("DELETE FROM Incident i WHERE i.id = :id")
    int deleteIncidentById(@Param("id") Long id);

    @Query("SELECT new com.dogsi.itil.dto.IdWithName(i.id,i.name) FROM Incident i")
    Page<IdWithName> getIdsAndNamesOfIncidents(Pageable pageable);

}
