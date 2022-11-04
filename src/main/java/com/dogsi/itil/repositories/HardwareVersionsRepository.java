package com.dogsi.itil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dogsi.itil.domain.configuration.HardwareVersion;


public interface HardwareVersionsRepository extends JpaRepository<HardwareVersion, Long>{
    
}
