package com.dogsi.itil.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.dogsi.itil.domain.incident.Incident;
import com.dogsi.itil.dto.IdWithName;
import com.dogsi.itil.dto.ItemByDay;
import com.dogsi.itil.dto.ItemByField;
import com.dogsi.itil.dto.ItemByPriority;
import com.dogsi.itil.dto.ItemBySatisfaction;

public interface IncidentRepository extends JpaRepository<Incident, Long>{
    
    @Transactional
    @Modifying
    @Query("DELETE FROM Incident i WHERE i.id = :id")
    int deleteIncidentById(@Param("id") Long id);

    @Query("SELECT new com.dogsi.itil.dto.IdWithName(i.id,i.name) FROM Incident i")
    Page<IdWithName> getIdsAndNamesOfIncidents(Pageable pageable);

    @Query("SELECT new com.dogsi.itil.dto.ItemByPriority(i.priority,COUNT(i.id)) FROM Incident i GROUP BY i.priority")
    List<ItemByPriority> countIncidentByPriority();

    @Query(value = "SELECT new com.dogsi.itil.dto.ItemByDay(i.reportedDate, COUNT(i.id)) "
        + " FROM Incident i" 
        + " GROUP BY i.reportedDate")
    List<ItemByDay> countIncidentByDay();

    @Query("SELECT new com.dogsi.itil.dto.ItemBySatisfaction(i.satisfaction,COUNT(i.id)) FROM Incident i GROUP BY i.satisfaction")
    List<ItemBySatisfaction> countIncidentBySatisfaction();


    @Query("SELECT new com.dogsi.itil.dto.ItemByField(i.category,COUNT(i.id)) FROM Incident i GROUP BY i.category")
    List<ItemByField> countIncidentByCategory();

    @Query("SELECT COUNT(i) FROM Incident i WHERE i.assignee='' OR i.assignee IS NULL")
    Long countIncidentsNotTaken();

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "DELETE FROM hardware_incident_relation i WHERE i.incident_id = :id")
    void deleteHardwareRelationships(Long id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "DELETE FROM sla_incident_relation i WHERE i.incident_id = :id")
    void deleteSlaRelationships(Long id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "DELETE FROM software_incident_relation i WHERE i.incident_id = :id")
    void deleteSoftwareRelationships(Long id);

    @Query("SELECT COUNT(h) FROM Incident i JOIN i.hardware h")
    Long countTotalHardware();

    @Query("SELECT COUNT(s) FROM Incident i JOIN i.sla s")
    Long countTotalSLa();

    @Query("SELECT COUNT(s) FROM Incident i JOIN i.software s")
    Long countTotalSoftware();
}
