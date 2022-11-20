package com.dogsi.itil.repositories;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dogsi.itil.domain.knownError.KnownError;
import com.dogsi.itil.dto.ItemByField;

public interface KnownErrorRepository extends JpaRepository<KnownError, Long>{
    
    @Transactional
    @Modifying
    @Query("DELETE FROM KnownError i WHERE i.id = :id")
    int deleteKnownErrorById(@Param("id") Long id);
}
