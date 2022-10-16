package com.dogsi.itil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.dogsi.itil.domain.configuration.Software;

public interface SoftwareRepository extends JpaRepository<Software, Long>{
    
    @Transactional
    @Modifying
    @Query("DELETE FROM Software s WHERE s.id = :id")
    int deleteSoftwareById(@Param("id") Long id);

}
