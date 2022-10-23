package com.dogsi.itil.domain.configuration;

import com.fasterxml.jackson.annotation.JsonIgnore;


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

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;


@Getter
@Setter
@Entity
@Table(name="software_versions")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class SoftwareVersion {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="software_version_id")
    private Long id;

    @Column(nullable = false)
    private Instant changeInstant;

    @Column(nullable = false)
    private Integer version;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="software_id")
    private Software software;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String softwareVersion;

    @Column(nullable = false)
    private String provider;

    @Column(nullable = false)
    private String license;

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false)
    private Instant acceptanceDate;

    @Column
    private String description;

    public SoftwareVersion(Integer version, Software software) {
        this.version = version;
        this.software = software;
        this.changeInstant = Instant.now();
        this.name = software.getName();
        this.type =software.getType();
        this.softwareVersion =software.getVersion();
        this.provider =software.getProvider();
        this.license =software.getLicense();
        this.origin =software.getOrigin();
        this.acceptanceDate =software.getAcceptanceDate();
        this.description =software.getDescription();
    }
}
