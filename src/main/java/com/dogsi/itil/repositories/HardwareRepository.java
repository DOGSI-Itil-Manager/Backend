package com.dogsi.itil.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.dogsi.itil.domain.configuration.Hardware;
import com.dogsi.itil.dto.ItemByField;

public interface HardwareRepository extends JpaRepository<Hardware, Long>{
    
    @Transactional
    @Modifying
    @Query("DELETE FROM Hardware h WHERE h.id = :id")
    int deleteHardwareById(@Param("id") Long id);

    @Query("SELECT SUM(h.price) FROM Hardware h")
    Float sumTotalSpentInHardware();

    @Query("SELECT new com.dogsi.itil.dto.ItemByField(h.type,COUNT(h.id)) FROM Hardware h GROUP BY h.type")
    List<ItemByField> countHardwareByType();
}
