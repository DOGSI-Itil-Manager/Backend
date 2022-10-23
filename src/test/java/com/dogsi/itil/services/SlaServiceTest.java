package com.dogsi.itil.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import com.dogsi.itil.dto.SlaDto;
import com.dogsi.itil.exceptions.ItemNotFoundException;
import com.dogsi.itil.repositories.SlaRepository;
import com.dogsi.itil.services.configuration.SlaService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.main.allow-bean-definition-overriding=true")
@ActiveProfiles("test-h2")
public class SlaServiceTest {
    
    @Autowired
    private SlaRepository repository;

    @Autowired
    private SlaService service;

    @AfterEach
    void tearDown(){
        repository.deleteAll();
    }

    @Test
    void shouldSaveASLA(){
        var dto = new SlaDto();
        dto.setCrucial(true);
        dto.setName("Name");
        dto.setStartDate(Instant.now());
        dto.setEndDate(Instant.now().plusSeconds(1600));
        dto.setService("Service");
        dto.setDescription("description");
        dto.setManager("manager");
        dto.setClient("Client");

        service.saveSla(dto);

        assertEquals(1, repository.count());

        var saved = repository.findAll().get(0);
        assertEquals("Name", saved.getName());
        assertTrue(saved.isCrucial());
    }

    @Test
    void shouldReturnAllSLAs(){
        var dto = new SlaDto();
        dto.setCrucial(true);
        dto.setName("Name");
        dto.setStartDate(Instant.now());
        dto.setEndDate(Instant.now().plusSeconds(1600));
        dto.setService("Service");
        dto.setDescription("description");
        dto.setManager("manager");
        dto.setClient("Client");

        service.saveSla(dto);
        service.saveSla(dto);
        service.saveSla(dto);

        var results = service.getSla(Pageable.unpaged());
        assertEquals(3, results.getTotalElements());
    }

    @Test
    void shouldThrowAnExceptionIfTheSLAIsNotFoundWhenUpdating(){
        var dto = new SlaDto();
        dto.setCrucial(true);
        dto.setName("Name");
        dto.setStartDate(Instant.now());
        dto.setEndDate(Instant.now().plusSeconds(1600));
        dto.setService("Service");
        dto.setDescription("description");
        dto.setManager("manager");
        dto.setClient("Client");

        assertThrows(ItemNotFoundException.class, () -> {
            service.updateSla(1L, dto);
        });
    }

    @Test
    void shouldThrowAnExceptionIfTheSLAIsNotFoundWhenDeleting(){
        assertThrows(ItemNotFoundException.class, () -> {
            service.deleteSla(1L);
        });
    }

    @Test
    void shouldDeleteASLA(){
        var dto = new SlaDto();
        dto.setCrucial(true);
        dto.setName("Name");
        dto.setStartDate(Instant.now());
        dto.setEndDate(Instant.now().plusSeconds(1600));
        dto.setService("Service");
        dto.setDescription("description");
        dto.setManager("manager");
        dto.setClient("Client");

        service.saveSla(dto);
        assertEquals(1, repository.count());
        var saved = repository.findAll().get(0);

        service.deleteSla(saved.getId());

        assertEquals(0, repository.count());
    }


    @Test
    void shouldUpdateASLa(){
        var dto = new SlaDto();
        dto.setCrucial(true);
        dto.setName("Name");
        dto.setStartDate(Instant.now());
        dto.setEndDate(Instant.now().plusSeconds(1600));
        dto.setService("Service");
        dto.setDescription("description");
        dto.setManager("manager");
        dto.setClient("Client");

        service.saveSla(dto);
        assertEquals(1, repository.count());
        var saved = repository.findAll().get(0);

        dto.setCrucial(false);
        
        service.updateSla(saved.getId(), dto);

        assertEquals(1, repository.count());
        saved = repository.findAll().get(0);
        assertFalse(saved.isCrucial());
        assertEquals(1, saved.getVersions().size());

        service.updateSla(saved.getId(), dto);

        assertEquals(1, repository.count());
        saved = repository.findAll().get(0);
        assertEquals(2, saved.getVersions().size());
    }

    @Test
    void shouldGetASLAById(){
        var dto = new SlaDto();
        dto.setCrucial(true);
        dto.setName("Name");
        dto.setStartDate(Instant.now());
        dto.setEndDate(Instant.now().plusSeconds(1600));
        dto.setService("Service");
        dto.setDescription("description");
        dto.setManager("manager");
        dto.setClient("Client");

        service.saveSla(dto);
        assertEquals(1, repository.count());
        var saved = repository.findAll().get(0);

        var itemById = service.getSlaById(saved.getId());

        assertEquals(dto.getName(), itemById.getName());
    }

    @Test
    void shouldThrowAnExceptionIfTheSLAIsNotFoundWhenGettingById(){
        assertThrows(ItemNotFoundException.class, () -> {
            service.getSlaById(1L);
        });
    }
}
