package com.dogsi.itil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dogsi.itil.domain.configuration.SLAVersion;

public interface SlaVersionsRepository extends JpaRepository<SLAVersion, Long>{
    
}
