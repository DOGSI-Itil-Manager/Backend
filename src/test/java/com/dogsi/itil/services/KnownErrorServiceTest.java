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
import com.dogsi.itil.domain.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.main.allow-bean-definition-overriding=true")
@ActiveProfiles("test-h2")
public class KnownErrorServiceTest {
    
    @Autowired
    private KnownErrorRepository repository;

    @Autowired
    private KnownErrorService service;

    @Autowired
    private ProblemService problemService;
    
    @Autowired
    private ProblemRepository problemRepository;

    @AfterEach
    void tearDown(){
        problemRepository.deleteAll();
        repository.deleteAll();
    }

    Long createProblemId(){
        var problemDto = new ProblemDto();
        problemDto.setName("Problem name");
        problemDto.setCategory("Problem category");
        problemDto.setReportedDate(Instant.now());
        problemDto.setPriority(Priority.ALTA);
        problemDto.setImpact(Impact.CRITICO);
        problemDto.setState(State.ABIERTO);
        problemService.saveProblem(problemDto);
        var problem = problemService.getProblem(Pageable.unpaged());
        return problem.getContent().get(0).getId();
    }

    @Test
    void shouldSaveAKnownError(){
        //var pid = createProblemId();

        var dto = new KnownErrorDto();
        dto.setName("Known Error name");
        dto.setCategory("known error category");
        dto.setCreationDate(Instant.now());
        dto.setDescription("known error description");
        dto.setRootcause("known error root cause");
        //dto.setProblemIds(pid);

        service.saveKnownError(dto);
        assertEquals(1, repository.count());
        var saved = repository.findAll().get(0);
        assertEquals("Known Error name", saved.getName());
    }

    @Test
    void shouldReturnAllKnownError(){
        for (var i=0;i<3;i++){
            var dto = new KnownErrorDto();
            dto.setName("Known Error name");
            dto.setCategory("Known Error category");
            dto.setCreationDate(Instant.now());
            dto.setDescription("Known Error description");
            dto.setRootcause("Known Error rootcause");
        
            //var pid = createProblemId();
            //dto.setProblemIds(pid);

            service.saveKnownError(dto);
        }

        var results = service.getKnownError(Pageable.unpaged());
        assertEquals(3, results.getTotalElements());
    }

    @Test
    void shouldThrowAnExceptionIfTheKnownErrorIsNotFoundWhenUpdating(){
        var dto = new KnownErrorDto();
        dto.setName("Known Error updated name");
        dto.setCategory("Known Error updated category");
        dto.setCreationDate(Instant.now());
        dto.setRootcause("Known Error updated rootcause");
        dto.setDescription("Known Error updated description");

        assertThrows(ItemNotFoundException.class, () -> {
            service.updateKnownError(1L, dto);
        });
    }

    @Test
    void shouldThrowAnExceptionIfTheKnownErrorIsNotFoundWhenDeleting(){
        assertThrows(ItemNotFoundException.class, () -> {
            service.deleteKnownError(1L);
        });
    }

    @Test
    void shouldDeleteAKnownError(){
        //var pid = createProblemId();

        var dto = new KnownErrorDto();
        dto.setName("Known Error name");
        dto.setCategory("Known Error category");
        dto.setCreationDate(Instant.now());
        dto.setRootcause("Known Error rootcause");
        dto.setDescription("Known Error description");
        //dto.setProblemIds(pid);

        service.saveKnownError(dto);
        assertEquals(1, repository.count());
        var saved = repository.findAll().get(0);

        service.deleteKnownError(saved.getId());

        assertEquals(0, repository.count());
    }

    @Test
    void shouldUpdateAKnownError(){
        //var pid = createProblemId();

        var dto = new KnownErrorDto();
        dto.setName("Known Error name");
        dto.setCategory("Known Error category");
        dto.setCreationDate(Instant.now());
        dto.setRootcause("Known Error rootcause");
        dto.setDescription("Known Error description");
        //dto.setProblemIds(pid);

        service.saveKnownError(dto);
        assertEquals(1, repository.count());
        var saved = repository.findAll().get(0);

        dto.setName("Known Error name2");
        
        service.updateKnownError(saved.getId(), dto);

        assertEquals(1, repository.count());
        saved = repository.findAll().get(0);
        assertEquals("Known Error name2", saved.getName());
    }
}
