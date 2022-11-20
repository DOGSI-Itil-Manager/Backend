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

import com.dogsi.itil.dto.KnownErrorDto;
import com.dogsi.itil.exceptions.ItemNotFoundException;
import com.dogsi.itil.repositories.KnownErrorRepository;
import com.dogsi.itil.services.knownError.KnownErrorService;

import com.dogsi.itil.dto.ProblemDto;
import com.dogsi.itil.repositories.ProblemRepository;
import com.dogsi.itil.services.problem.ProblemService;
import com.dogsi.itil.domain.problem.Problem;
import com.dogsi.itil.domain.incident.enums.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.main.allow-bean-definition-overriding=true")
@ActiveProfiles("test-h2")
public class KnownErrorServiceTest {
    
    @Autowired
    private KnownErrorRepository repository;

    @Autowired
    private KnownErrorService service;

    @Autowired
    private ProblemService problemService;

    @AfterEach
    void tearDown(){
        repository.deleteAll();
    }

    //@Test
    void shouldSaveAKnownError(){
        var problemdto = new ProblemDto();
        problemdto.setName("Problem name");
        problemdto.setCategory("Problem category");
        problemdto.setReportedDate(Instant.now());
        problemdto.setDescription("Problem description");
        problemdto.setEmailOfUserInCharge("test@test.com");
        problemdto.setPriority(Priority.ALTA);
        problemdto.setImpact(Impact.CRITICO);
        problemdto.setState(State.ABIERTO);
        problemService.saveProblem(problemdto);
        var problem = problemService.getProblem(Pageable.unpaged());
        assertEquals(1, problem.getContent().size());
        var pid = problem.getContent().get(0).getId();

        var dto = new KnownErrorDto();
        dto.setName("Known Error name");
        dto.setCategory("known error category");
        dto.setDescription("known error description");
        dto.setCreationDate(Instant.now());
        dto.setRootcause("known error root cause");
        dto.setProblemId(pid);

        service.saveKnownError(dto);

        assertEquals(1, repository.count());

        var saved = repository.findAll().get(0);
        assertEquals("Known Error name", saved.getName());
        
    }

    //@Test
    void shouldReturnAllKnownError(){
        var dto = new KnownErrorDto();
        dto.setName("Known Error name");
        dto.setCategory("Known Error category");
        dto.setCreationDate(Instant.now());
        dto.setDescription("Known Error description");
        dto.setRootcause("Known Error rootcause");
        service.saveKnownError(dto);

        var results = service.getKnownError(Pageable.unpaged());
        assertEquals(3, results.getTotalElements());
    }

    //@Test
    void shouldThrowAnExceptionIfTheKnownErrorIsNotFoundWhenUpdating(){
        var dto = new KnownErrorDto();
        dto.setName("Known Error name");
        dto.setCategory("Known Error category");
        dto.setRootcause("Known Error rootcause");
        dto.setCreationDate(Instant.now());
        dto.setDescription("Known Error description");

        assertThrows(ItemNotFoundException.class, () -> {
            service.updateKnownError(1L, dto);
        });
    }

    //@Test
    void shouldThrowAnExceptionIfTheKnownErrorIsNotFoundWhenDeleting(){
        assertThrows(ItemNotFoundException.class, () -> {
            service.deleteKnownError(1L);
        });
    }

    //@Test
    void shouldDeleteAKnownError(){
        var dto = new KnownErrorDto();
        dto.setName("Known Error name");
        dto.setCategory("Known Error category");
        dto.setRootcause("Known Error rootcause");
        dto.setCreationDate(Instant.now());
        dto.setDescription("Known Error description");

        service.saveKnownError(dto);
        assertEquals(1, repository.count());
        var saved = repository.findAll().get(0);

        service.deleteKnownError(saved.getId());

        assertEquals(0, repository.count());
    }

    //@Test
    void shouldUpdateAKnownError(){
        var dto = new KnownErrorDto();
        dto.setName("Known Error name");
        dto.setCategory("Known Error category");
        dto.setRootcause("Known Error rootcause");
        dto.setCreationDate(Instant.now());
        dto.setDescription("Known Error description");

        service.saveKnownError(dto);
        assertEquals(1, repository.count());
        var saved = repository.findAll().get(0);

        dto.setName("Name2");
        
        service.updateKnownError(saved.getId(), dto);

        assertEquals(1, repository.count());
        saved = repository.findAll().get(0);
        assertEquals("Name2", saved.getName());
    }
}
