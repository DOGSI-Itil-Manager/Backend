package com.dogsi.itil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.dogsi.itil.domain.problem.Problem;

public interface ProblemRepository extends JpaRepository<Problem, Long>{
    
    @Transactional
    @Modifying
    @Query("DELETE FROM Problem i WHERE i.id = :id")
    int deleteProblemById(@Param("id") Long id);

}
