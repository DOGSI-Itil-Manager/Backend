package com.dogsi.itil.components;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
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
import com.dogsi.itil.domain.changes.Change;
import com.dogsi.itil.domain.incident.Incident;

import com.dogsi.itil.dto.ChangeDto;
import com.dogsi.itil.dto.HardwareDto;
import com.dogsi.itil.dto.IncidentDto;
import com.dogsi.itil.dto.ProblemDto;
import com.dogsi.itil.dto.SlaDto;
import com.dogsi.itil.dto.SoftwareDto;
import com.dogsi.itil.repositories.ChangeRepository;
import com.dogsi.itil.services.change.ChangeService;
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
    private HardwareService hardwareService;

    @Autowired
    private SlaService slaService;

    @Autowired
    private SoftwareService softwareService;

    @Autowired
    private IncidentService incidentService;

    @Autowired
    private ProblemService problemService;

    @Autowired
    private ChangeService changeService;

    @Autowired
    private ChangeRepository changeRepository;

    public void run(ApplicationArguments args) {

        loadHardwareData();
    
        loadSoftwareData();

        loadSlaData();
    
        loadIncidentData();

        loadProblemData();

        loadChangesData();
    }

    private void loadChangesData() {
        var incidentIds = new ArrayList<Long>();
        var problemsIds = new ArrayList<Long>();

        changeService.saveChange(new ChangeDto("Mejora de Windows", "OS",
            Priority.ALTA,Impact.SERIO,
            "Hay que actualizar el OS",null,
            State.ABIERTO,incidentIds,
            problemsIds,""));

        for(int i=0;i<20;i++){
            var priority = Priority.values()[(int) (Math.random() * 100) % 4];
            var impact = Impact.values()[(int) (Math.random() * 100) % 4];
            var daysToSubstract = (long) (Math.random() * 100) % 24;
            var openedDate = LocalDate.now().minusDays(daysToSubstract+5);
            var category = "Example category " + String.valueOf((int)(Math.random() * 100) % 4);
            var name = "A change name " + String.valueOf(i);
            var state = State.values()[(int) (Math.random() * 100) % 6];
            var assignee = state.equals(State.ABIERTO) ? "" : "email@test.com" + String.valueOf(i % 5);
            var description = "A description " + String.valueOf(i);
            var closedDate = state.equals(State.ABIERTO) ? null : Date.valueOf(LocalDate.now().minusDays(daysToSubstract).plusDays(5));
            var change = Change.builder()
                .name(name)
                .category(category)
                .closedDate(closedDate)
                .description(description)
                .emailOfUserInCharge(assignee)
                .impact(impact)
                .state(state)
                .priority(priority)
                .build();
            change.setOpenedDate(openedDate);
            changeRepository.save(change);
        }
    }

    private void loadProblemData() {
        var incidents = incidentService.getIncident(Pageable.unpaged()).getContent();
        var ids = new ArrayList<Long>();
        for (Incident incident : incidents) {
            if(incident.getCategory().equals("OS"))
                ids.add(incident.getId());
        }
        problemService.saveProblem(new ProblemDto("Falla en Windows 10","OS",Priority.MEDIA,Impact.SERIO,State.ABIERTO,"Windows 10 continua con pantalla azul",LocalDate.now(),null,ids,"jortiz@mail.com"));

        for(int i=0;i<10;i++){
            var priority = Priority.values()[(int) (Math.random() * 100) % 4];
            var impact = Impact.values()[(int) (Math.random() * 100) % 4];
            var daysToSubstract = (long) (Math.random() * 100) % 10;
            var openedDate = LocalDate.now().minusDays(daysToSubstract+3);
            var category = "Example category " + String.valueOf((int)(Math.random() * 100) % 6);
            var name = "A problem name " + String.valueOf(i);
            var state = State.values()[(int) (Math.random() * 100) % 6];
            var assignee = state.equals(State.ABIERTO) ? "" : "An asignee email" + String.valueOf(i % 5);
            var description = "A description " + String.valueOf(i);
            var closedDate = state.equals(State.ABIERTO) ? null : Date.valueOf(LocalDate.now().minusDays(daysToSubstract).plusDays(3));
            
            var testProblem = new ProblemDto(name, category, priority, impact, state, description,openedDate, closedDate,new ArrayList<>(),assignee);
            problemService.saveProblem(testProblem);
        }
    }

    private void loadIncidentData() {

        incidentService.saveIncident(new IncidentDto("Falla en inicio de Windows 10 2","OS",Priority.MEDIA,Impact.SERIO,
            State.ABIERTO,"","Tiro pantalla azul",LocalDate.now().minusDays(1), null,null,null,null,null));
        incidentService.saveIncident(new IncidentDto("Falla en inicio de Photoshop","Edicion",Priority.MEDIA,Impact.MARGINAL,
            State.ASIGNADO,"Joaquin Ortiz","No se logro abrir correctamente el programa",LocalDate.now(), null,null,null,null,null));
        incidentService.saveIncident(new IncidentDto("Falla en inicio de Windows 10 1","OS",Priority.MEDIA,Impact.SERIO,
            State.CERRADO,"Joaquin Ortiz","Tiro pantalla azul",LocalDate.now().minusDays(3), Date.from(Instant.now()),Satisfaction.MEDIA,null,null,null));
    
        

        for(int i=0;i<27;i++){
            var priority = Priority.values()[(int) (Math.random() * 100) % 4];
            var impact = Impact.values()[(int) (Math.random() * 100) % 4];
            var daysToSubstract = (long) (Math.random() * 100) % 24;
            var openedDate = LocalDate.now().minusDays(daysToSubstract+5);
            var category = "Example category " + String.valueOf((int)(Math.random() * 100) % 6);
            var name = "An incident name " + String.valueOf(i);
            var state = State.values()[(int) (Math.random() * 100) % 6];
            var assignee = state.equals(State.ABIERTO) ? "" : "An asignee name" + String.valueOf(i % 5);
            var description = "A description " + String.valueOf(i);
            var closedDate = state.equals(State.ABIERTO) ? null : Date.valueOf(LocalDate.now().minusDays(daysToSubstract).plusDays(5));
            var satisfaction = state.equals(State.ABIERTO) ? null : Satisfaction.values()[(int) (Math.random() * 100) % 5];
            var testIncident = new IncidentDto(name, category, priority, impact, state, assignee, description, openedDate, closedDate, satisfaction,null,null,null);
            incidentService.saveIncident(testIncident);
        }
    
    }

    private void loadSlaData() {
        slaService.saveSla(new SlaDto("Servicio de internet", "Internet", false,"Juan Perez", "Telecentro", SLASide.PROVIDER, Instant.now(), Instant.now().plusSeconds(600000), "Servicio de internet de 100MB"));
        slaService.saveSla(new SlaDto("Servicio de internet", "Internet", true,"Joaquin Ortiz", "IPLAN", SLASide.PROVIDER, Instant.now(), Instant.now().plusSeconds(600000), "Servicio de internet de 200MB"));
        slaService.saveSla(new SlaDto("Atencion al cliente", "Atencion al cliente", true,"Joaquin Ortiz", "AtteCl", SLASide.CLIENT, Instant.now(), Instant.now().plusSeconds(600000), "Servicio de atencion al cliente"));
    
        var sla1 = slaService.getSla(Pageable.unpaged()).getContent().get(0);
        slaService.updateSla(sla1.getId(), new SlaDto("Servicio de internet", "Internet", false,"Juan Manuel Perez", "Telecentro", SLASide.PROVIDER, Instant.now(), Instant.now().plusSeconds(600000), "Servicio de internet de 100MB") );
    }

    private void loadSoftwareData() {
        softwareService.saveSoftware(new SoftwareDto("Windows 10", "OS", "H10.11","Microsoft","Privada","USA",Instant.now(),"Sistema operativo"));
        softwareService.saveSoftware(new SoftwareDto("Windows 11", "OS", "H11.01","Microsoft","Privada","USA",Instant.now(),"Sistema operativo"));
        softwareService.saveSoftware(new SoftwareDto("Ubuntu 20.2", "OS", "20.2","Canonical","Libre","South Africa",Instant.now(),"Sistema operativo"));
        softwareService.saveSoftware(new SoftwareDto("Photoshop", "Edicion", "11.22.355","Adobe","Privado","USA",Instant.now(),"Programa de edicion"));
    
        var soft1 = softwareService.getSoftware(Pageable.unpaged()).getContent().get(0);
        softwareService.updateSoftware(soft1.getId(), new SoftwareDto("Windows 10", "OS", "H10.12","Microsoft","Privada","USA",Instant.now(),"Sistema operativo"));
        softwareService.updateSoftware(soft1.getId(), new SoftwareDto("Windows 10", "OS", "H10.13","Microsoft","Privada","USA",Instant.now(),"Sistema operativo"));
        softwareService.updateSoftware(soft1.getId(), new SoftwareDto("Windows 10", "OS", "H10.14","Microsoft","Privada","USA",Instant.now(),"Sistema operativo"));
        softwareService.updateSoftware(soft1.getId(), new SoftwareDto("Windows 10", "OS", "H10.15","Microsoft","Privada","USA",Instant.now(),"Sistema operativo"));
        softwareService.updateSoftware(soft1.getId(), new SoftwareDto("Windows 10", "OS", "H10.16","Microsoft","Privada","USA",Instant.now(),"Sistema operativo"));
    }

    private void loadHardwareData() {
        hardwareService.saveHardware(new HardwareDto("RTX 3070", "GPU", "ASDX13465", "D1", "Nvidia", 1500f, Instant.now(), "Placa grafica"));
        hardwareService.saveHardware(new HardwareDto("RTX 3060", "GPU", "ASDX58289", "D2", "Nvidia", 1000f, Instant.now(), "Placa grafica"));
        hardwareService.saveHardware(new HardwareDto("RX 570", "GPU", "RTX29487", "D2", "AMD", 500f, Instant.now(), "Placa grafica"));
        hardwareService.saveHardware(new HardwareDto("I5 2300", "CPU", "I23123", "D1", "Intel", 350f, Instant.now(), "Procesador"));
    }

}
