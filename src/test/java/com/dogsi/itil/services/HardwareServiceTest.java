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

import com.dogsi.itil.dto.HardwareDto;
import com.dogsi.itil.exceptions.ItemNotFoundException;
import com.dogsi.itil.repositories.HardwareRepository;
import com.dogsi.itil.services.configuration.HardwareService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.main.allow-bean-definition-overriding=true")
@ActiveProfiles("test-h2")
public class HardwareServiceTest {
    
    @Autowired
    private HardwareRepository repository;

    @Autowired
    private HardwareService service;

    @AfterEach
    void tearDown(){
        repository.deleteAll();
    }

    @Test
    void shouldSaveAHardware(){
        var dto = new HardwareDto();
        dto.setName("Name");
        dto.setAdditionDate(Instant.now());
        dto.setDescription("description");
        dto.setLocation("Location");
        dto.setProvider("Provider");
        dto.setSerialNumber("SerialNumber");
        dto.setType("type");
        dto.setPrice(122f);

        service.saveHardware(dto);

        assertEquals(1, repository.count());

        var saved = repository.findAll().get(0);
        assertEquals("Name", saved.getName());
    }

    @Test
    void shouldReturnAllHardware(){
        var dto = new HardwareDto();
        dto.setName("Name");
        dto.setAdditionDate(Instant.now());
        dto.setDescription("description");
        dto.setLocation("Location");
        dto.setProvider("Provider");
        dto.setSerialNumber("SerialNumber");
        dto.setType("type");
        dto.setPrice(122f);

        service.saveHardware(dto);
        service.saveHardware(dto);
        service.saveHardware(dto);

        var results = service.getHardware(Pageable.unpaged());
        assertEquals(3, results.getTotalElements());
    }

    @Test
    void shouldThrowAnExceptionIfTheHardwareIsNotFoundWhenUpdating(){
        var dto = new HardwareDto();
        dto.setName("Name");
        dto.setAdditionDate(Instant.now());
        dto.setDescription("description");
        dto.setLocation("Location");
        dto.setProvider("Provider");
        dto.setSerialNumber("SerialNumber");
        dto.setType("type");
        dto.setPrice(122f);

        assertThrows(ItemNotFoundException.class, () -> {
            service.updateHardware(1L, dto);
        });
    }

    @Test
    void shouldThrowAnExceptionIfTheHardwareIsNotFoundWhenDeleting(){
        assertThrows(ItemNotFoundException.class, () -> {
            service.deleteHardware(1L);
        });
    }

    @Test
    void shouldDeleteAHardware(){
        var dto = new HardwareDto();
        dto.setName("Name");
        dto.setAdditionDate(Instant.now());
        dto.setDescription("description");
        dto.setLocation("Location");
        dto.setProvider("Provider");
        dto.setSerialNumber("SerialNumber");
        dto.setType("type");
        dto.setPrice(122f);

        service.saveHardware(dto);
        assertEquals(1, repository.count());
        var saved = repository.findAll().get(0);

        service.deleteHardware(saved.getId());

        assertEquals(0, repository.count());
    }


    @Test
    void shouldUpdateAHardware(){
        var dto = new HardwareDto();
        dto.setName("Name");
        dto.setAdditionDate(Instant.now());
        dto.setDescription("description");
        dto.setLocation("Location");
        dto.setProvider("Provider");
        dto.setSerialNumber("SerialNumber");
        dto.setType("type");
        dto.setPrice(122f);

        service.saveHardware(dto);
        assertEquals(1, repository.count());
        var saved = repository.findAll().get(0);

        dto.setName("Name2");
        
        service.updateHardware(saved.getId(), dto);

        assertEquals(1, repository.count());
        saved = repository.findAll().get(0);
        assertEquals("Name2", saved.getName());
        assertEquals(1, saved.getVersions().size());

        service.updateHardware(saved.getId(), dto);

        assertEquals(1, repository.count());
        saved = repository.findAll().get(0);
        assertEquals(2, saved.getVersions().size());

    }
}
