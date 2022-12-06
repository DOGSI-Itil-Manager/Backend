package com.dogsi.itil.services.incident.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dogsi.itil.domain.incident.Incident;
import com.dogsi.itil.dto.IdWithName;
import com.dogsi.itil.dto.IncidentDto;
import com.dogsi.itil.exceptions.ItemNotFoundException;
import com.dogsi.itil.repositories.HardwareRepository;
import com.dogsi.itil.repositories.IncidentRepository;
import com.dogsi.itil.repositories.SlaRepository;
import com.dogsi.itil.repositories.SoftwareRepository;
import com.dogsi.itil.services.incident.IncidentService;

@Service
public class IncidentServiceImpl implements IncidentService {

    @Autowired
    private IncidentRepository repository;

    @Autowired
    private HardwareRepository hardwareRepository;

    @Autowired
    private SoftwareRepository softwareRepository;

    @Autowired
    private SlaRepository slaRepository;


    @Override
    public void saveIncident(IncidentDto dto) {
        var incident = Incident.builder()
                .name(dto.getName())
                .category(dto.getCategory())
                .priority(dto.getPriority())
                .impact(dto.getImpact())
                .state(dto.getState())
                .assignee(dto.getAssignee())
                .description(dto.getDescription())
                .reportedDate(dto.getReportedDate())
                .closedDate(dto.getClosedDate())
                .satisfaction(dto.getSatisfaction())
                .build();

        var ids = dto.getHardware();
        if(ids!=null && !ids.isEmpty()){
            var hardware = hardwareRepository.findAllById(ids);
            if(hardware.size() != ids.size()) {
                throw new ItemNotFoundException("Hardware not found");
            }
            incident.addHardwares(hardware);
        }

        ids = dto.getSla();
        if(ids!=null && !ids.isEmpty()){
            var sla = slaRepository.findAllById(ids);
            if(sla.size() != ids.size()) {
                throw new ItemNotFoundException("SLA not found");
            }
            incident.addSlas(sla);
        }

        ids = dto.getSoftware();
        if(ids!=null && !ids.isEmpty()){
            var software = softwareRepository.findAllById(ids);
            if(software.size() != ids.size()) {
                throw new ItemNotFoundException("Software not found");
            }
            incident.addSoftwares(software);
        }
        repository.save(incident);
    }

    @Override
    public Page<Incident> getIncident(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void updateIncident(Long id, IncidentDto dto) {
        repository.deleteHardwareRelationships(id);
        repository.deleteSlaRelationships(id);
        repository.deleteSoftwareRelationships(id);

        var incident = repository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Incident with id " + id + " not found");
        });


        var ids = dto.getHardware();
        if(ids!=null && !ids.isEmpty()){
            var hardware = hardwareRepository.findAllById(ids);
            if(hardware.size() != ids.size()) {
                throw new ItemNotFoundException("Hardware not found");
            }
            incident.addHardwares(hardware);
        }

        ids = dto.getSla();
        if(ids!=null && !ids.isEmpty()){
            var sla = slaRepository.findAllById(ids);
            if(sla.size() != ids.size()) {
                throw new ItemNotFoundException("SLA not found");
            }
            incident.addSlas(sla);
        }

        ids = dto.getSoftware();
        if(ids!=null && !ids.isEmpty()){
            var software = softwareRepository.findAllById(ids);
            if(software.size() != ids.size()) {
                throw new ItemNotFoundException("Software not found");
            }
            incident.addSoftwares(software);
        }


        incident.setName(dto.getName());
        incident.setCategory(dto.getCategory());
        incident.setPriority(dto.getPriority());
        incident.setImpact(dto.getImpact());
        incident.setState(dto.getState());
        incident.setAssignee(dto.getAssignee());
        incident.setDescription(dto.getDescription());
        incident.setReportedDate(dto.getReportedDate());
        incident.setClosedDate(dto.getClosedDate());
        incident.setSatisfaction(dto.getSatisfaction());

        repository.save(incident);
    }

    @Override
    public void deleteIncident(Long id) {
        int deleted = repository.deleteIncidentById(id);
        if (deleted == 0) {
            throw new ItemNotFoundException("Incident with id " + id + " not found");
        }
    }

    public Incident getIncidentById(Long id) {
        return repository.findById(id).orElseThrow(() -> {throw new ItemNotFoundException("Incident with id " + id + " not found");});
    }

    @Override
    public Page<IdWithName> getIncidentIdsWithNames(Pageable pageable) {
        return repository.getIdsAndNamesOfIncidents(pageable);
    }
}
