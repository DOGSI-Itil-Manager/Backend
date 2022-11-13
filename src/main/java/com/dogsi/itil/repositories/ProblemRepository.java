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

public interface ProblemRepository extends JpaRepository<Problem, Long>{
    
    @Transactional
    @Modifying
    @Query("DELETE FROM Problem i WHERE i.id = :id")
    int deleteProblemById(@Param("id") Long id);

    @Query("SELECT new com.dogsi.itil.dto.ItemByField(p.emailOfUserInCharge,COUNT(p.id)) FROM Problem p GROUP BY emailOfUserInCharge")
    List<ItemByField> countProblemsByUserInCharge();

    @Query("SELECT new com.dogsi.itil.dto.IdWithName(p.id,p.name) FROM Problem p")
    Page<IdWithName> getIdsAndNamesOfProblems(Pageable pageable);
}
