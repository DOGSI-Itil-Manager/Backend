package com.dogsi.itil.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dogsi.itil.domain.configuration.SLA;

public interface SlaRepository extends JpaRepository<SLA, Long>{
    @Transactional
    @Modifying
    @Query("DELETE FROM SLA s WHERE s.id = :id")
    int deleteSlaById(@Param("id") Long id);
}
