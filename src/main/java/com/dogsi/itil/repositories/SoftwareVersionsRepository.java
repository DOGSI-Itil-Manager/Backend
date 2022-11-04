package com.dogsi.itil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dogsi.itil.domain.configuration.SoftwareVersion;


public interface SoftwareVersionsRepository extends JpaRepository<SoftwareVersion, Long>{
    
}
