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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;

@Getter
@Setter
@Entity
@Table(name="hardware_versions")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class HardwareVersions {
    
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="hardware_version_id")
    private Long id;

    @Column(nullable = false)
    private Instant changeInstant;

    @Column(nullable = false)
    private Integer version;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="hardware_id")
    private Hardware hardware;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String serialNumber;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String provider;

    @Column(nullable = false)
    private Float price;

    @Column(nullable = false)
    private Instant additionDate;

    @Column
    private String description;

    public HardwareVersions(Integer version, Hardware hardware) {
        this.version = version;
        this.changeInstant = Instant.now();
        this.hardware = hardware;
        this.name = hardware.getName();
        this.type = hardware.getType();
        this.serialNumber = hardware.getSerialNumber();
        this.location = hardware.getLocation();
        this.provider = hardware.getProvider();
        this.price = hardware.getPrice();
        this.additionDate = hardware.getAdditionDate();
        this.description = hardware.getDescription();
    }
}
