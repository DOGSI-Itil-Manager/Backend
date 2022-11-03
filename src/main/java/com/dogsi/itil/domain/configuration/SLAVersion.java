package com.dogsi.itil.domain.configuration;


import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dogsi.itil.domain.SLASide;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;
import lombok.Builder;

@Getter
@Setter
@Entity
@Table(name="sla_versions")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class SLAVersion {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sla_version_id")
    private Long id;

    @Column(nullable = false)
    private Instant changeInstant;

    @Column(nullable = false)
    private Integer version;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="sla_id")
    private SLA sla;

    
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

    public SLAVersion(Integer version, SLA sla) {
        this.version = version;
        this.sla = sla;
        this.changeInstant = Instant.now();
        this.name = sla.getName();
        this.service = sla.getService();
        this.crucial = sla.isCrucial();
        this.manager = sla.getManager();
        this.sideName = sla.getSideName();
        this.sideType = sla.getSideType();
        this.startDate = sla.getStartDate();
        this.endDate = sla.getEndDate();
        this.description = sla.getDescription();
    }
}
