package com.dogsi.itil.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dogsi.itil.domain.configuration.SLA;
import com.dogsi.itil.dto.ItemByField;

public interface SlaRepository extends JpaRepository<SLA, Long>{
    @Transactional
    @Modifying
    @Query("DELETE FROM SLA s WHERE s.id = :id")
    int deleteSlaById(@Param("id") Long id);


    @Query("SELECT COUNT(s) FROM SLA s WHERE s.crucial = TRUE")
    int countCrucialSlas();

    @Query("SELECT new com.dogsi.itil.dto.ItemByField(s.manager,COUNT(s.id)) FROM SLA s GROUP BY s.manager")
    List<ItemByField> countSlasByManager();

    @Query("SELECT new com.dogsi.itil.dto.ItemByField(s.sideName,COUNT(s.id)) FROM SLA s WHERE s.sideType=com.dogsi.itil.domain.SLASide.CLIENT GROUP BY s.sideName")
    List<ItemByField> countSlasByClient();

    @Query("SELECT new com.dogsi.itil.dto.ItemByField(s.sideName,COUNT(s.id)) FROM SLA s WHERE s.sideType=com.dogsi.itil.domain.SLASide.PROVIDER GROUP BY s.sideName")
    List<ItemByField> countSlasByProvider();

    @Query("SELECT new com.dogsi.itil.dto.ItemByField(s.service,COUNT(s.id)) FROM SLA s GROUP BY s.service")
    List<ItemByField> countSlasByService();
}

