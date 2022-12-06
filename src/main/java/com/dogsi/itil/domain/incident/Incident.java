package com.dogsi.itil.domain.incident;

import com.dogsi.itil.domain.problem.Problem;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.FetchType;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.dogsi.itil.domain.Impact;
import com.dogsi.itil.domain.Priority;
import com.dogsi.itil.domain.Satisfaction;
import com.dogsi.itil.domain.State;
import com.dogsi.itil.domain.changes.Change;
import com.dogsi.itil.domain.configuration.Hardware;
import com.dogsi.itil.domain.configuration.SLA;
import com.dogsi.itil.domain.configuration.Software;


@Getter
@Setter
@Entity
@Table(name = "incident")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Incident {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "incident_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private Priority priority;
    @Column(nullable = false)
    private Impact impact;
    @Column(nullable = false)
    private State state;

    @Column
    private String assignee;

    @Column
    private String description;

    @Column(nullable = false)
    private LocalDate reportedDate;

    @Column
    private Date closedDate;

    @Column
    private Satisfaction satisfaction;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinTable(
        name = "problem_incident_relation", 
        joinColumns = @JoinColumn(name = "incident_id"), 
        inverseJoinColumns = @JoinColumn(name = "problem_id"))
    private List<Problem> problems;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinTable(
        name = "change_incident_relation", 
        joinColumns = @JoinColumn(name = "incident_id"), 
        inverseJoinColumns = @JoinColumn(name = "change_id"))
    private List<Change> changes;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinTable(
        name = "hardware_incident_relation", 
        joinColumns = @JoinColumn(name = "incident_id"), 
        inverseJoinColumns = @JoinColumn(name = "hardware_id"))
    private List<Hardware> hardwares;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinTable(
        name = "sla_incident_relation", 
        joinColumns = @JoinColumn(name = "incident_id"), 
        inverseJoinColumns = @JoinColumn(name = "sla_id"))
    private List<SLA> slas;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinTable(
        name = "software_incident_relation", 
        joinColumns = @JoinColumn(name = "incident_id"), 
        inverseJoinColumns = @JoinColumn(name = "software_id"))
    private List<Software> softwares;

    @Builder
    public Incident(String name, String category, Priority priority, Impact impact, State state, String assignee, String description,
        LocalDate reportedDate, Date closedDate, Satisfaction satisfaction) {
        this.name = name;
        this.category = category;
        this.priority = priority;
        this.impact = impact;
        this.state = state;
        this.assignee = assignee;
        this.description = description;
        this.reportedDate = reportedDate;
        this.closedDate = closedDate;
        this.satisfaction = satisfaction;
        this.softwares = new ArrayList<>();
        this.slas = new ArrayList<>();
        this.hardwares = new ArrayList<>();
    }

    public void addHardwares(List<Hardware> hardware) {
        this.hardwares.addAll(hardware);
    }

    public void addSoftwares(List<Software> softwares) {
        this.softwares.addAll(softwares);
    }

    public void addSlas(List<SLA> slas) {
        this.slas.addAll(slas);
    }
}
