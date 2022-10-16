package com.dogsi.itil.domain.configuration;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;

@Getter
@Setter
@Entity
@Table(name="software")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Software {

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="software_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String version;

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

    @Builder
    public Software(String name, String type, String version, String provider, String license, String origin,
            Instant acceptanceDate, String description) {
        this.name = name;
        this.type = type;
        this.version = version;
        this.provider = provider;
        this.license = license;
        this.origin = origin;
        this.acceptanceDate = acceptanceDate;
        this.description = description;
    }
}
