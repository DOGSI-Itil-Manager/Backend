package com.dogsi.itil.services.configuration.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dogsi.itil.domain.configuration.Software;
import com.dogsi.itil.domain.configuration.SoftwareVersion;
import com.dogsi.itil.dto.SoftwareDto;
import com.dogsi.itil.exceptions.ItemNotFoundException;
import com.dogsi.itil.repositories.SoftwareRepository;
import com.dogsi.itil.services.configuration.SoftwareService;

@Service
public class SoftwareServiceImpl implements SoftwareService{

    private SoftwareRepository repository;
    
    public SoftwareServiceImpl(SoftwareRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveSoftware(SoftwareDto dto) {
        var software = Software.builder()
            .name(dto.getName())
            .type(dto.getType())
            .provider(dto.getProvider())
            .license(dto.getLicense())
            .acceptanceDate(dto.getAcceptanceDate())
            .description(dto.getDescription())
            .origin(dto.getOrigin())
            .softwareVersion(dto.getSoftwareVersion())
            .build();
        repository.save(software);
    }

    @Override
    public Page<Software> getSoftware(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void updateSoftware(Long id, SoftwareDto dto) {
        var software = repository.findById(id).orElseThrow(() -> {throw new ItemNotFoundException("Software with id " + id + " not found");});
        var version = new SoftwareVersion(software.getVersions().size(), software);
        software.addVersion(version);
        
        software.setName(dto.getName());
        software.setType(dto.getType());
        software.setProvider(dto.getProvider());
        software.setDescription(dto.getDescription());
        software.setAcceptanceDate(dto.getAcceptanceDate());
        software.setLicense(dto.getLicense());
        software.setSoftwareVersion(dto.getSoftwareVersion());
        software.setOrigin(dto.getOrigin());
        repository.save(software);
    }

    @Override
    public void deleteSoftware(Long id) {
        int deleted = repository.deleteSoftwareById(id);
        if (deleted == 0){
            throw new ItemNotFoundException("Software with id " + id + " not found");
        }
    }

    @Override
    public Software getSoftwareById(Long id) {
        return repository.findById(id).orElseThrow(() -> {throw new ItemNotFoundException("Software with id " + id + " not found");});
    }
}
