package com.dogsi.itil.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.dogsi.itil.domain.problem.workaround.Workaround;
import com.dogsi.itil.dto.IdWithNamesOfWorkaround;

public interface WorkaroundRepository extends JpaRepository<Workaround, Long>{
    
    @Transactional
    @Modifying
    @Query("DELETE FROM Workaround i WHERE i.id = :id")
    int deleteWorkaroundById(@Param("id") Long id);

    @Query("SELECT new com.dogsi.itil.dto.IdWithNamesOfWorkaround(i.id,i.name) FROM Workaround i")
    Page<IdWithNamesOfWorkaround> getIdsAndNamesOfWorkarounds(Pageable pageable);

}
