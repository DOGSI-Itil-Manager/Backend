package com.dogsi.itil.services.configuration.impl;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dogsi.itil.domain.configuration.Hardware;
import com.dogsi.itil.dto.HardwareDto;
import com.dogsi.itil.exceptions.ItemNotFoundException;
import com.dogsi.itil.repositories.HardwareRepository;
import com.dogsi.itil.services.configuration.HardwareService;

@Service
public class HardwareServiceImpl implements HardwareService{
    
    private HardwareRepository repository;

    public HardwareServiceImpl(HardwareRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveHardware(HardwareDto dto) {
        var hardware = Hardware.builder()
            .name(dto.getName())
            .type(dto.getType())
            .serialNumber(dto.getSerialNumber())
            .location(dto.getLocation())
            .provider(dto.getProvider())
            .price(dto.getPrice())
            .additionDate(dto.getAdditionDate())
            .description(dto.getDescription())
            .build();
        repository.save(hardware);
    }

    @Override
    public Page<Hardware> getHardware(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void updateHardware(Long id, HardwareDto dto) {
        var hardware = repository.findById(id).orElseThrow(() -> {throw new ItemNotFoundException("Hardware with id " + id + " not found");});
        hardware.setName(dto.getName());
        hardware.setType(dto.getType());
        hardware.setSerialNumber(dto.getSerialNumber());
        hardware.setLocation(dto.getLocation());
        hardware.setProvider(dto.getProvider());
        hardware.setPrice(dto.getPrice());
        hardware.setAdditionDate(dto.getAdditionDate());
        hardware.setDescription(dto.getDescription());
        repository.save(hardware);
    }

    @Override
    public void deleteHardware(Long id) {
        int deleted = repository.deleteHardwareById(id);
        if (deleted == 0){
            throw new ItemNotFoundException("Hardware with id " + id + " not found");
        }
    }
}
