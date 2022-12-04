package com.dogsi.itil.repositories;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dogsi.itil.domain.knownError.solution.Solution;
import com.dogsi.itil.dto.IdWithName;
import com.dogsi.itil.dto.ItemByField;

public interface SolutionRepository extends JpaRepository<Solution, Long>{
    
    @Transactional
    @Modifying
    @Query("DELETE FROM Solution s WHERE s.id = :id")
    int deleteSolutionById(@Param("id") Long id);

    @Query("SELECT new com.dogsi.itil.dto.IdWithName(s.id,s.name) FROM Solution s")
    Page<IdWithName> getIdsAndNames(Pageable pageable);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "DELETE FROM solutions_known_errors_relation k WHERE k.solution_id = :id")
    void deleteKnownErrorRelationships(Long id);
}