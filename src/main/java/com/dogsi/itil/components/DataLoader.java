package com.dogsi.itil.components;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.dogsi.itil.domain.SLASide;
import com.dogsi.itil.dto.HardwareDto;
import com.dogsi.itil.dto.SlaDto;
import com.dogsi.itil.dto.SoftwareDto;
import com.dogsi.itil.services.configuration.HardwareService;
import com.dogsi.itil.services.configuration.SlaService;
import com.dogsi.itil.services.configuration.SoftwareService;

import lombok.extern.slf4j.Slf4j;

@ConditionalOnProperty(name="load-data", havingValue = "true")
@Component
@Slf4j
public class DataLoader implements ApplicationRunner {

    @Autowired
    private HardwareService hardware;

    @Autowired
    private SlaService sla;

    @Autowired
    private SoftwareService software;

    public void run(ApplicationArguments args) {

        hardware.saveHardware(new HardwareDto("RTX 3070", "GPU", "ASDX13465", "D1", "Nvidia", 1500f, Instant.now(), "Placa grafica"));
        hardware.saveHardware(new HardwareDto("RTX 3060", "GPU", "ASDX58289", "D2", "Nvidia", 1000f, Instant.now(), "Placa grafica"));
        hardware.saveHardware(new HardwareDto("RX 570", "GPU", "RTX29487", "D2", "AMD", 500f, Instant.now(), "Placa grafica"));
        hardware.saveHardware(new HardwareDto("I5 2300", "CPU", "I23123", "D1", "Intel", 350f, Instant.now(), "Procesador"));
    
        software.saveSoftware(new SoftwareDto("Windows 10", "OS", "H10.11","Microsoft","Privada","USA",Instant.now(),"Sistema operativo"));
        software.saveSoftware(new SoftwareDto("Windows 11", "OS", "H11.01","Microsoft","Privada","USA",Instant.now(),"Sistema operativo"));
        software.saveSoftware(new SoftwareDto("Ubuntu 20.2", "OS", "20.2","Canonical","Libre","South Africa",Instant.now(),"Sistema operativo"));
        software.saveSoftware(new SoftwareDto("Photoshop", "Edicion", "11.22.355","Adobe","Privado","USA",Instant.now(),"Programa de edicion"));
    
        sla.saveSla(new SlaDto("Servicio de internet", "Internet", false,"Juan Perez", "Telecentro", SLASide.PROVIDER, Instant.now(), Instant.now().plusSeconds(600000), "Servicio de internet de 100MB"));
        sla.saveSla(new SlaDto("Servicio de internet", "Internet", true,"Joaquin Ortiz", "IPLAN", SLASide.PROVIDER, Instant.now(), Instant.now().plusSeconds(600000), "Servicio de internet de 200MB"));
        sla.saveSla(new SlaDto("Atencion al cliente", "Atencion al cliente", true,"Joaquin Ortiz", "AtteCl", SLASide.CLIENT, Instant.now(), Instant.now().plusSeconds(600000), "Servicio de atencion al cliente"));
    
    
    }
}
