package com.dogsi.itil.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import com.dogsi.itil.domain.Impact;
import com.dogsi.itil.domain.Priority;
import com.dogsi.itil.domain.State;
import com.dogsi.itil.dto.IncidentDto;
import com.dogsi.itil.dto.ProblemDto;
import com.dogsi.itil.exceptions.ItemNotFoundException;
import com.dogsi.itil.repositories.ProblemRepository;
import com.dogsi.itil.services.incident.IncidentService;
import com.dogsi.itil.services.problem.ProblemService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.main.allow-bean-definition-overriding=true")
@ActiveProfiles("test-h2")
public class ProblemServiceTest {
    
    @Autowired
    private ProblemRepository repository;

    @Autowired
    private ProblemService service;

    @Autowired
    private IncidentService incidentService;

    @AfterEach
    void tearDown(){
        repository.deleteAll();
    }

    @Test
    void shouldSaveAProblem(){
        var dto = new ProblemDto();
        dto.setName("Name");
        dto.setCategory("capa 8");
        dto.setPriority(Priority.ALTA);
        dto.setImpact(Impact.CRITICO);
        dto.setReportedDate(Instant.now());
        dto.setDescription("description");
        dto.setState(State.ABIERTO);
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
        dto.setPriority(Priority.ALTA);
        dto.setImpact(Impact.CRITICO);
        dto.setReportedDate(Instant.now());
        dto.setDescription("description");
        dto.setState(State.ABIERTO);
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
        dto.setPriority(Priority.ALTA);
        dto.setImpact(Impact.CRITICO);
        dto.setReportedDate(Instant.now());
        dto.setDescription("description");
        dto.setState(State.ABIERTO);
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
    void shouldDeleteAProblem(){
        var dto = new ProblemDto();
        dto.setName("Name");
        dto.setCategory("capa 8");
        dto.setPriority(Priority.ALTA);
        dto.setImpact(Impact.CRITICO);
        dto.setReportedDate(Instant.now());
        dto.setDescription("description");
        dto.setState(State.ABIERTO);
        dto.setClosedDate(new Date());

        service.saveProblem(dto);
        assertEquals(1, repository.count());
        var saved = repository.findAll().get(0);

        service.deleteProblem(saved.getId());

        assertEquals(0, repository.count());
    }

    @Test
    void shouldUpdateAProblem(){
        var dto = new ProblemDto();
        dto.setName("Name");
        dto.setCategory("capa 8");
        dto.setPriority(Priority.ALTA);
        dto.setImpact(Impact.CRITICO);
        dto.setReportedDate(Instant.now());
        dto.setDescription("description");
        dto.setState(State.ABIERTO);
        // dto.setClosedDate(new Date());
        var incidentIds = new ArrayList<Long>();

        dto.setIncidentIds(incidentIds);
        dto.setEmailOfUserInCharge("test@test.com");

        service.saveProblem(dto);
        assertEquals(1, repository.count());
        var saved = repository.findAll().get(0);

        dto.setName("Name2");
        
        service.updateProblem(saved.getId(), dto);

        assertEquals(1, repository.count());
        saved = repository.findAll().get(0);
        assertEquals("Name2", saved.getName());
    }

        @Test
        void shouldAssignAnInidentToAProblem(){
            var incident = new IncidentDto();
            incident.setName("Name");
            incident.setCategory("capa 8");
            incident.setPriority(Priority.ALTA);
            incident.setImpact(Impact.CRITICO);
            incident.setReportedDate(Instant.now());
            incident.setDescription("description");
            incident.setState(State.ABIERTO);
            incident.setAssignee("Nadie");
            incident.setClosedDate(new Date());
    
            incidentService.saveIncident(incident);
            var incidents = incidentService.getIncident(Pageable.unpaged());
            assertEquals(1, incidents.getContent().size());
            var incidentId = incidents.getContent().get(0).getId();

            var dto = new ProblemDto();
            dto.setName("Name");
            dto.setCategory("capa 8");
            dto.setPriority(Priority.ALTA);
            dto.setImpact(Impact.CRITICO);
            dto.setReportedDate(Instant.now());
            dto.setDescription("description");
            dto.setState(State.ABIERTO);
            // dto.setClosedDate(new Date());
            var incidentIds = new ArrayList<Long>();
            incidentIds.add(incidentId);
            dto.setIncidentIds(incidentIds);
            dto.setEmailOfUserInCharge("test@test.com");
    
            service.saveProblem(dto);
            assertEquals(1, repository.count());
            var saved = repository.findAll().get(0);
    
            assertEquals("Name", saved.getName());
            assertEquals(1, saved.getIncidents().size());
            assertEquals(incidentId, saved.getIncidents().get(0).getId());
        }
}
