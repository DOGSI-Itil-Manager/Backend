package com.dogsi.itil.components;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.dogsi.itil.domain.Impact;
import com.dogsi.itil.domain.Priority;
import com.dogsi.itil.domain.SLASide;
import com.dogsi.itil.domain.Satisfaction;
import com.dogsi.itil.domain.State;
import com.dogsi.itil.domain.incident.Incident;
import com.dogsi.itil.dto.HardwareDto;
import com.dogsi.itil.dto.IncidentDto;
import com.dogsi.itil.dto.ProblemDto;
import com.dogsi.itil.dto.SlaDto;
import com.dogsi.itil.dto.SoftwareDto;
import com.dogsi.itil.services.configuration.HardwareService;
import com.dogsi.itil.services.configuration.SlaService;
import com.dogsi.itil.services.configuration.SoftwareService;
import com.dogsi.itil.services.incident.IncidentService;
import com.dogsi.itil.services.problem.ProblemService;

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

    @Autowired
    private IncidentService incident;

    @Autowired
    private ProblemService problem;

    public void run(ApplicationArguments args) {

        loadHardwareData();
    
        loadSoftwareData();

        loadSlaData();
    
        loadIncidentData();

        loadProblemData();
    }

    private void loadProblemData() {
        var incidents = incident.getIncident(Pageable.unpaged()).getContent();
        var ids = new ArrayList<Long>();
        for (Incident incident : incidents) {
            if(incident.getCategory().equals("OS"))
                ids.add(incident.getId());
        }
        problem.saveProblem(new ProblemDto("Falla en Windows 10","OS",Priority.MEDIA,Impact.SERIO,State.ABIERTO,"Windows 10 continua con pantalla azul",Instant.now(),null,ids,"jortiz@mail.com"));

    }

    private void loadIncidentData() {
        incident.saveIncident(new IncidentDto("Falla en inicio de Windows 10 2","OS",Priority.MEDIA,Impact.SERIO,
            State.ABIERTO,"","Tiro pantalla azul",Instant.now(), null,null));
        incident.saveIncident(new IncidentDto("Falla en inicio de Photoshop","Edicion",Priority.MEDIA,Impact.MARGINAL,
            State.ASIGNADO,"Joaquin Ortiz","No se logro abrir correctamente el programa",Instant.now(), null,null));
        incident.saveIncident(new IncidentDto("Falla en inicio de Windows 10 1","OS",Priority.MEDIA,Impact.SERIO,
            State.CERRADO,"Joaquin Ortiz","Tiro pantalla azul",Instant.now().minusSeconds(50000), Date.from(Instant.now()),Satisfaction.MEDIA));
    }

    private void loadSlaData() {
        sla.saveSla(new SlaDto("Servicio de internet", "Internet", false,"Juan Perez", "Telecentro", SLASide.PROVIDER, Instant.now(), Instant.now().plusSeconds(600000), "Servicio de internet de 100MB"));
        sla.saveSla(new SlaDto("Servicio de internet", "Internet", true,"Joaquin Ortiz", "IPLAN", SLASide.PROVIDER, Instant.now(), Instant.now().plusSeconds(600000), "Servicio de internet de 200MB"));
        sla.saveSla(new SlaDto("Atencion al cliente", "Atencion al cliente", true,"Joaquin Ortiz", "AtteCl", SLASide.CLIENT, Instant.now(), Instant.now().plusSeconds(600000), "Servicio de atencion al cliente"));
    
        var sla1 = sla.getSla(Pageable.unpaged()).getContent().get(0);
        sla.updateSla(sla1.getId(), new SlaDto("Servicio de internet", "Internet", false,"Juan Manuel Perez", "Telecentro", SLASide.PROVIDER, Instant.now(), Instant.now().plusSeconds(600000), "Servicio de internet de 100MB") );
    }

    private void loadSoftwareData() {
        software.saveSoftware(new SoftwareDto("Windows 10", "OS", "H10.11","Microsoft","Privada","USA",Instant.now(),"Sistema operativo"));
        software.saveSoftware(new SoftwareDto("Windows 11", "OS", "H11.01","Microsoft","Privada","USA",Instant.now(),"Sistema operativo"));
        software.saveSoftware(new SoftwareDto("Ubuntu 20.2", "OS", "20.2","Canonical","Libre","South Africa",Instant.now(),"Sistema operativo"));
        software.saveSoftware(new SoftwareDto("Photoshop", "Edicion", "11.22.355","Adobe","Privado","USA",Instant.now(),"Programa de edicion"));
    
        var soft1 = software.getSoftware(Pageable.unpaged()).getContent().get(0);
        software.updateSoftware(soft1.getId(), new SoftwareDto("Windows 10", "OS", "H10.12","Microsoft","Privada","USA",Instant.now(),"Sistema operativo"));
        software.updateSoftware(soft1.getId(), new SoftwareDto("Windows 10", "OS", "H10.13","Microsoft","Privada","USA",Instant.now(),"Sistema operativo"));
        software.updateSoftware(soft1.getId(), new SoftwareDto("Windows 10", "OS", "H10.14","Microsoft","Privada","USA",Instant.now(),"Sistema operativo"));
        software.updateSoftware(soft1.getId(), new SoftwareDto("Windows 10", "OS", "H10.15","Microsoft","Privada","USA",Instant.now(),"Sistema operativo"));
        software.updateSoftware(soft1.getId(), new SoftwareDto("Windows 10", "OS", "H10.16","Microsoft","Privada","USA",Instant.now(),"Sistema operativo"));
    }

    private void loadHardwareData() {
        hardware.saveHardware(new HardwareDto("RTX 3070", "GPU", "ASDX13465", "D1", "Nvidia", 1500f, Instant.now(), "Placa grafica"));
        hardware.saveHardware(new HardwareDto("RTX 3060", "GPU", "ASDX58289", "D2", "Nvidia", 1000f, Instant.now(), "Placa grafica"));
        hardware.saveHardware(new HardwareDto("RX 570", "GPU", "RTX29487", "D2", "AMD", 500f, Instant.now(), "Placa grafica"));
        hardware.saveHardware(new HardwareDto("I5 2300", "CPU", "I23123", "D1", "Intel", 350f, Instant.now(), "Procesador"));
    }

}
