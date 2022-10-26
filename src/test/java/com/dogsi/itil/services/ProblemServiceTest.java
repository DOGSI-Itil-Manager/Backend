package com.dogsi.itil.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Instant;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import com.dogsi.itil.dto.ProblemDto;
import com.dogsi.itil.exceptions.ItemNotFoundException;
import com.dogsi.itil.repositories.ProblemRepository;
import com.dogsi.itil.services.problem.ProblemService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.main.allow-bean-definition-overriding=true")
@ActiveProfiles("test-h2")
public class ProblemServiceTest {
    
    @Autowired
    private ProblemRepository repository;

    @Autowired
    private ProblemService service;

    @AfterEach
    void tearDown(){
        repository.deleteAll();
    }

    @Test
    void shouldSaveAnProblem(){
        var dto = new ProblemDto();
        dto.setName("Name");
        dto.setCategory("capa 8");
        dto.setPriority("P1");
        dto.setImpact("High");
        dto.setReportedDate(Instant.now());
        dto.setDescription("description");
        dto.setState("Open");
        dto.setClosedDate(new Date());

        service.saveProblem(dto);

        assertEquals(1, repository.count());

        var saved = repository.findAll().get(0);
        assertEquals("Name", saved.getName());
    }

    @Test
    void shouldReturnAllProblem(){
        var dto = new ProblemDto();
        dto.setName("Name");
        dto.setCategory("capa 8");
        dto.setPriority("P1");
        dto.setImpact("High");
        dto.setReportedDate(Instant.now());
        dto.setDescription("description");
        dto.setState("Open");
        dto.setClosedDate(new Date());

        service.saveProblem(dto);
        service.saveProblem(dto);
        service.saveProblem(dto);

        var results = service.getProblem(Pageable.unpaged());
        assertEquals(3, results.getTotalElements());
    }

    @Test
    void shouldThrowAnExceptionIfTheProblemIsNotFoundWhenUpdating(){
        var dto = new ProblemDto();
        dto.setName("Name");
        dto.setCategory("capa 8");
        dto.setPriority("P1");
        dto.setImpact("High");
        dto.setReportedDate(Instant.now());
        dto.setDescription("description");
        dto.setState("Open");
        dto.setClosedDate(new Date());

        assertThrows(ItemNotFoundException.class, () -> {
            service.updateProblem(1L, dto);
        });
    }

    @Test
    void shouldThrowAnExceptionIfTheProblemIsNotFoundWhenDeleting(){
        assertThrows(ItemNotFoundException.class, () -> {
            service.deleteProblem(1L);
        });
    }

    @Test
    void shouldDeleteAnProblem(){
        var dto = new ProblemDto();
        dto.setName("Name");
        dto.setCategory("capa 8");
        dto.setPriority("P1");
        dto.setImpact("High");
        dto.setReportedDate(Instant.now());
        dto.setDescription("description");
        dto.setState("Open");
        dto.setClosedDate(new Date());

        service.saveProblem(dto);
        assertEquals(1, repository.count());
        var saved = repository.findAll().get(0);

        service.deleteProblem(saved.getId());

        assertEquals(0, repository.count());
    }


    @Test
    void shouldUpdateAnProblem(){
        var dto = new ProblemDto();
        dto.setName("Name");
        dto.setCategory("capa 8");
        dto.setPriority("P1");
        dto.setImpact("High");
        dto.setReportedDate(Instant.now());
        dto.setDescription("description");
        dto.setState("Open");
        dto.setClosedDate(new Date());

        service.saveProblem(dto);
        assertEquals(1, repository.count());
        var saved = repository.findAll().get(0);

        dto.setName("Name2");
        
        service.updateProblem(saved.getId(), dto);

        assertEquals(1, repository.count());
        saved = repository.findAll().get(0);
        assertEquals("Name2", saved.getName());
    }
}
