package com.dogsi.itil.repositories;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dogsi.itil.domain.problem.Problem;
import com.dogsi.itil.dto.IdWithName;
import com.dogsi.itil.dto.ItemByField;
import com.dogsi.itil.dto.ItemByDay;
import com.dogsi.itil.dto.ItemByPriority;
import com.dogsi.itil.dto.ItemBySatisfaction;
import com.dogsi.itil.dto.ItemByState;

public interface ProblemRepository extends JpaRepository<Problem, Long>{
    
    @Transactional
    @Modifying
    @Query("DELETE FROM Problem i WHERE i.id = :id")
    int deleteProblemById(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "DELETE FROM problem_incident_relation p WHERE p.problem_id = :id")
    int deleteIncidentRelationships(@Param("id")Long id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "DELETE FROM known_error_problem_relation k WHERE k.known_error_id = :id")
    int deleteProblemsKnownErrorRelationships(@Param("id")Long id);
    
    @Query("SELECT new com.dogsi.itil.dto.ItemByField(p.emailOfUserInCharge,COUNT(p.id)) FROM Problem p GROUP BY emailOfUserInCharge")
    List<ItemByField> countProblemsByUserInCharge();

    @Query("SELECT new com.dogsi.itil.dto.IdWithName(p.id,p.name) FROM Problem p")
    Page<IdWithName> getIdsAndNamesOfProblems(Pageable pageable);

    @Query("SELECT new com.dogsi.itil.dto.ItemByPriority(p.priority,COUNT(p.id)) FROM Problem p GROUP BY p.priority")
    List<ItemByPriority> countProblemByPriority();

    @Query("SELECT new com.dogsi.itil.dto.ItemByDay(p.reportedDate,COUNT(p.id)) FROM Problem p GROUP BY p.reportedDate")
    List<ItemByDay> countProblemByDay();

    @Query("SELECT new com.dogsi.itil.dto.ItemByField(p.category,COUNT(p.id)) FROM Problem p GROUP BY p.category")
    List<ItemByField> countProblemByCategory();

    @Query("SELECT COUNT(i) FROM Problem p JOIN p.incidents i")
    Long countIncidentsInProblems();

    @Query("SELECT new com.dogsi.itil.dto.ItemByState(p.state,COUNT(p.id)) FROM Problem p GROUP BY p.state")
    List<ItemByState> countProblemByState();

    @Query("SELECT avg(datediff(hour,reportedDate,closedDate)) FROM Problem p")
    Long averageProblemLifetime();
}
