package com.dogsi.itil.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import com.dogsi.itil.dto.IncidentDto;
import com.dogsi.itil.exceptions.ItemNotFoundException;
import com.dogsi.itil.repositories.IncidentRepository;
import com.dogsi.itil.services.incident.IncidentService;
import com.dogsi.itil.domain.Impact;
import com.dogsi.itil.domain.Priority;
import com.dogsi.itil.domain.Satisfaction;
import com.dogsi.itil.domain.State;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.main.allow-bean-definition-overriding=true")
@ActiveProfiles("test-h2")
public class IncidentServiceTest {
    
    @Autowired
    private IncidentRepository repository;

    @Autowired
    private IncidentService service;

    @AfterEach
    void tearDown(){
        repository.deleteAll();
        repository.flush();
    }

    @Test
    void shouldSaveAnIncident(){
        var dto = new IncidentDto();
        dto.setName("Name");
        dto.setCategory("capa 8");
        dto.setPriority(Priority.BAJA);
        dto.setImpact(Impact.CRITICO);
        dto.setReportedDate(LocalDate.now());
        dto.setDescription("description");
        dto.setState(State.ABIERTO);
        dto.setAssignee("Nadie");
        dto.setClosedDate(new Date());
        dto.setSatisfaction(Satisfaction.ALTA);

        service.saveIncident(dto);

        assertEquals(1, repository.count());

        var saved = repository.findAll().get(0);
        assertEquals("Name", saved.getName());
    }

    @Test
    void shouldReturnAllIncident(){
        var dto = new IncidentDto();
        dto.setName("Name");
        dto.setCategory("capa 8");
        dto.setPriority(Priority.BAJA);
        dto.setImpact(Impact.CRITICO);
        dto.setReportedDate(LocalDate.now());
        dto.setDescription("description");
        dto.setState(State.ABIERTO);
        dto.setAssignee("Nadie");
        dto.setClosedDate(new Date());
        dto.setSatisfaction(Satisfaction.ALTA);

        service.saveIncident(dto);
        service.saveIncident(dto);
        service.saveIncident(dto);

        var results = service.getIncident(Pageable.unpaged());
        assertEquals(3, results.getTotalElements());
    }

    @Test
    void shouldThrowAnExceptionIfTheIncidentIsNotFoundWhenUpdating(){
        var dto = new IncidentDto();
        dto.setName("Name");
        dto.setCategory("capa 8");
        dto.setPriority(Priority.BAJA);
        dto.setImpact(Impact.CRITICO);
        dto.setReportedDate(LocalDate.now());
        dto.setDescription("description");
        dto.setState(State.ABIERTO);
        dto.setAssignee("Nadie");
        dto.setClosedDate(new Date());
        dto.setSatisfaction(Satisfaction.ALTA);


        assertThrows(ItemNotFoundException.class, () -> {
            service.updateIncident(1L, dto);
        });
    }

    @Test
    void shouldThrowAnExceptionIfTheIncidentIsNotFoundWhenDeleting(){
        assertThrows(ItemNotFoundException.class, () -> {
            service.deleteIncident(1L);
        });
    }

    @Test
    void shouldDeleteAnIncident(){
        var dto = new IncidentDto();
        dto.setName("Name");
        dto.setCategory("capa 8");
        dto.setPriority(Priority.BAJA);
        dto.setImpact(Impact.CRITICO);
        dto.setReportedDate(LocalDate.now());
        dto.setDescription("description");
        dto.setState(State.ABIERTO);
        dto.setAssignee("Nadie");
        dto.setClosedDate(new Date());
        dto.setSatisfaction(Satisfaction.ALTA);

        service.saveIncident(dto);
        assertEquals(1, repository.count());
        var saved = repository.findAll().get(0);

        service.deleteIncident(saved.getId());

        assertEquals(0, repository.count());
    }


    @Test
    void shouldUpdateAnIncident(){
        var dto = new IncidentDto();
        dto.setName("Name");
        dto.setCategory("capa 8");
        dto.setPriority(Priority.BAJA);
        dto.setImpact(Impact.CRITICO);
        dto.setReportedDate(LocalDate.now());
        dto.setDescription("description");
        dto.setState(State.ABIERTO);
        dto.setAssignee("Nadie");
        dto.setClosedDate(new Date());
        dto.setSatisfaction(Satisfaction.ALTA);

        service.saveIncident(dto);
        assertEquals(1, repository.count());
        var saved = repository.findAll().get(0);

        dto.setName("Name2");
        
        service.updateIncident(saved.getId(), dto);

        assertEquals(1, repository.count());
        saved = repository.findAll().get(0);
        assertEquals("Name2", saved.getName());
    }
}
