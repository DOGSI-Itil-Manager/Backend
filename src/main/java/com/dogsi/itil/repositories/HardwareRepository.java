package com.dogsi.itil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.dogsi.itil.domain.configuration.Hardware;

public interface HardwareRepository extends JpaRepository<Hardware, Long>{
    
    @Transactional
    @Modifying
    @Query("DELETE FROM Hardware h WHERE h.id = :id")
    int deleteHardwareById(@Param("id") Long id);

}
