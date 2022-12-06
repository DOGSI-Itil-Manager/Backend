package com.dogsi.itil.domain.configuration;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.dogsi.itil.domain.SLASide;
import com.dogsi.itil.domain.incident.Incident;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;

@Getter
@Setter
@Entity
@Table(name="sla")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class SLA {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sla_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String service;

    @Column(nullable = false)
    private boolean crucial;

    @Column(nullable = false)
    private String manager;

    @Column(nullable = false)
    private String sideName;

    @Column(nullable = false)
    private SLASide sideType;

    @Column(nullable = false)
    private Instant startDate;

    @Column(nullable = false)
    private Instant endDate;

    @Column
    private String description;

    @OneToMany(mappedBy = "sla",fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
    private List<SLAVersion> versions;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinTable(
        name = "sla_incident_relation", 
        joinColumns = @JoinColumn(name = "sla_id"), 
        inverseJoinColumns = @JoinColumn(name = "incident_id"))
    private List<Incident> incidents;

    @Builder
    public SLA(String name, String service, boolean crucial, String manager, String sideName, Instant startDate,
            Instant endDate, String description, SLASide sideType) {
        this.name = name;
        this.service = service;
        this.crucial = crucial;
        this.manager = manager;
        this.sideName = sideName;
        this.sideType = sideType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.versions = new ArrayList<>();
    }

    public void addVersion(SLAVersion version){
        versions.add(version);
    }
}
