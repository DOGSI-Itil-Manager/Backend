package com.dogsi.itil.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.dogsi.itil.domain.configuration.Software;
import com.dogsi.itil.dto.ItemByField;

public interface SoftwareRepository extends JpaRepository<Software, Long>{
    
    @Transactional
    @Modifying
    @Query("DELETE FROM Software s WHERE s.id = :id")
    int deleteSoftwareById(@Param("id") Long id);

    @Query("SELECT new com.dogsi.itil.dto.ItemByField(s.type,COUNT(s.id)) FROM Software s GROUP BY s.type")
    List<ItemByField> countSoftwareByType();
}
