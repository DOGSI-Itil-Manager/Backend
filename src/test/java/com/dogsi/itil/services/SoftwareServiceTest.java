package com.dogsi.itil.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Instant;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;


import com.dogsi.itil.dto.SoftwareDto;
import com.dogsi.itil.exceptions.ItemNotFoundException;
import com.dogsi.itil.repositories.SoftwareRepository;
import com.dogsi.itil.services.configuration.SoftwareService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.main.allow-bean-definition-overriding=true")
@ActiveProfiles("test-h2")
public class SoftwareServiceTest {
    
    @Autowired
    private SoftwareRepository repository;

    @Autowired
    private SoftwareService service;

    @AfterEach
    void tearDown(){
        repository.deleteAll();
    }

    @Test
    void shouldSaveASoftware(){
        var dto = new SoftwareDto();
        dto.setName("Name");
        dto.setAcceptanceDate(Instant.now());
        dto.setDescription("description");
        dto.setLicense("License");
        dto.setProvider("Provider");
        dto.setOrigin("Origin");
        dto.setType("type");
        dto.setSoftwareVersion("Version");

        service.saveSoftware(dto);

        assertEquals(1, repository.count());

        var saved = repository.findAll().get(0);
        assertEquals("Name", saved.getName());
    }

    @Test
    void shouldReturnAllSoftware(){
        var dto = new SoftwareDto();
        dto.setName("Name");
        dto.setAcceptanceDate(Instant.now());
        dto.setDescription("description");
        dto.setLicense("License");
        dto.setProvider("Provider");
        dto.setOrigin("Origin");
        dto.setType("type");
        dto.setSoftwareVersion("Version");

        service.saveSoftware(dto);
        service.saveSoftware(dto);
        service.saveSoftware(dto);

        var results = service.getSoftware(Pageable.unpaged());
        assertEquals(3, results.getTotalElements());
    }

    @Test
    void shouldThrowAnExceptionIfTheSoftwareIsNotFoundWhenUpdating(){
        var dto = new SoftwareDto();
        dto.setName("Name");
        dto.setAcceptanceDate(Instant.now());
        dto.setDescription("description");
        dto.setLicense("License");
        dto.setProvider("Provider");
        dto.setOrigin("Origin");
        dto.setType("type");
        dto.setSoftwareVersion("Version");

        assertThrows(ItemNotFoundException.class, () -> {
            service.updateSoftware(1L, dto);
        });
    }

    @Test
    void shouldThrowAnExceptionIfTheSoftwareIsNotFoundWhenDeleting(){
        assertThrows(ItemNotFoundException.class, () -> {
            service.deleteSoftware(1L);
        });
    }

    @Test
    void shouldDeleteASoftware(){
        var dto = new SoftwareDto();
        dto.setName("Name");
        dto.setAcceptanceDate(Instant.now());
        dto.setDescription("description");
        dto.setLicense("License");
        dto.setProvider("Provider");
        dto.setOrigin("Origin");
        dto.setType("type");
        dto.setSoftwareVersion("Version");

        service.saveSoftware(dto);
        assertEquals(1, repository.count());
        var saved = repository.findAll().get(0);

        service.deleteSoftware(saved.getId());

        assertEquals(0, repository.count());
    }


    @Test
    void shouldUpdateASoftware(){
        var dto = new SoftwareDto();
        dto.setName("Name");
        dto.setAcceptanceDate(Instant.now());
        dto.setDescription("description");
        dto.setLicense("License");
        dto.setProvider("Provider");
        dto.setOrigin("Origin");
        dto.setType("type");
        dto.setSoftwareVersion("Version");

        service.saveSoftware(dto);
        assertEquals(1, repository.count());
        var saved = repository.findAll().get(0);

        dto.setName("Name2");
        
        service.updateSoftware(saved.getId(), dto);

        assertEquals(1, repository.count());
        saved = repository.findAll().get(0);
        assertEquals("Name2", saved.getName());
        assertEquals(1, saved.getVersions().size());

        service.updateSoftware(saved.getId(), dto);

        assertEquals(1, repository.count());
        saved = repository.findAll().get(0);
        assertEquals(2, saved.getVersions().size());
    }

    @Test
    void shouldGetASoftwareById(){
        var dto = new SoftwareDto();
        dto.setName("Name");
        dto.setAcceptanceDate(Instant.now());
        dto.setDescription("description");
        dto.setLicense("License");
        dto.setProvider("Provider");
        dto.setOrigin("Origin");
        dto.setType("type");
        dto.setSoftwareVersion("Version");

        service.saveSoftware(dto);
        assertEquals(1, repository.count());
        var saved = repository.findAll().get(0);

        var itemById = service.getSoftwareById(saved.getId());

        assertEquals(dto.getName(), itemById.getName());
    }

    @Test
    void shouldThrowAnExceptionIfTheSoftwareIsNotFoundWhenGettingById(){
        assertThrows(ItemNotFoundException.class, () -> {
            service.getSoftwareById(1L);
        });
    }
}
