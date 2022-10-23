package com.dogsi.itil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.dogsi.itil.domain.incident.Incident;

public interface IncidentRepository extends JpaRepository<Incident, Long>{
    
    @Transactional
    @Modifying
    @Query("DELETE FROM Incident i WHERE i.id = :id")
    int deleteIncidentById(@Param("id") Long id);

}
