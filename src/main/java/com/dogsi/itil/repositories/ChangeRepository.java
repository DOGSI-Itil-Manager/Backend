package com.dogsi.itil.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.dogsi.itil.domain.changes.Change;
import com.dogsi.itil.dto.ItemByField;

public interface ChangeRepository extends JpaRepository<Change, Long>{

    @Transactional
    @Modifying
    @Query("DELETE FROM Change c WHERE c.id = :id")
    int deleteChangeById(@Param("id") Long id);

    @Query("SELECT COUNT(c) FROM Change c WHERE c.emailOfUserInCharge='' OR c.emailOfUserInCharge IS NULL")
    Long countChangesNotTaken();

    @Query("SELECT COUNT(i) FROM Change c JOIN c.incidents i")
    Long countIncidentsInChanges();

    @Query("SELECT COUNT(p) FROM Change c JOIN c.problems p")
    Long countProblemsInChanges();

    @Query("SELECT new com.dogsi.itil.dto.ItemByField(c.category,COUNT(c.id)) FROM Change c GROUP BY c.category")
    List<ItemByField> countChangeByCategory();

    @Query("SELECT COUNT(c) FROM Change c WHERE c.state = com.dogsi.itil.domain.State.CANCELADO")
    Long countCancelledChanges();


}
