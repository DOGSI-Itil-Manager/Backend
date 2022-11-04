package com.dogsi.itil.services.configuration.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dogsi.itil.domain.configuration.SLA;
import com.dogsi.itil.domain.configuration.SLAVersion;
import com.dogsi.itil.dto.SlaDto;
import com.dogsi.itil.exceptions.ItemNotFoundException;
import com.dogsi.itil.repositories.SlaRepository;
import com.dogsi.itil.services.configuration.SlaService;

@Service
public class SlaServiceImpl implements SlaService{

    private SlaRepository repository;

    public SlaServiceImpl(SlaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveSla(SlaDto dto) {
        var sla = SLA.builder()
            .name(dto.getName())
            .description(dto.getDescription())
            .crucial(dto.getCrucial())
            .sideName(dto.getSideName())
            .sideType(dto.getSideType())
            .startDate(dto.getStartDate())
            .endDate(dto.getEndDate())
            .service(dto.getService())
            .manager(dto.getManager())
            .build();
        repository.save(sla);
    }

    @Override
    public Page<SLA> getSla(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void updateSla(Long id, SlaDto dto) {
        var sla = repository.findById(id).orElseThrow(() -> {throw new ItemNotFoundException("SLA with id " + id + " not found");});
        var version = new SLAVersion(sla.getVersions().size(), sla);
        sla.addVersion(version);
        sla.setName(dto.getName());
        sla.setDescription(dto.getDescription());
        sla.setCrucial(dto.getCrucial());
        sla.setSideName(dto.getSideName());
        sla.setSideType(dto.getSideType());
        sla.setStartDate(dto.getStartDate());
        sla.setEndDate(dto.getEndDate());
        sla.setService(dto.getService());
        sla.setManager(dto.getManager());
        repository.save(sla);
    }

    @Override
    public void deleteSla(Long id) {
        int deleted = repository.deleteSlaById(id);
        if (deleted == 0){
            throw new ItemNotFoundException("SLA with id " + id + " not found");
        }
    }

    @Override
    public SLA getSlaById(Long id) {
        return repository.findById(id).orElseThrow(() -> {throw new ItemNotFoundException("SLA with id " + id + " not found");});
    }
    
}
