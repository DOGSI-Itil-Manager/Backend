package com.dogsi.itil.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.dogsi.itil.domain.changes.Change;

public interface ChangeRepository extends JpaRepository<Change, Long>{

    @Transactional
    @Modifying
    @Query("DELETE FROM Change c WHERE c.id = :id")
    int deleteChangeById(@Param("id") Long id);
}
