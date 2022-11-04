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
import javax.persistence.OneToMany;
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

    @OneToMany(mappedBy = "software",fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
    private List<SoftwareVersion> versions;

    @Builder
    public Software(String name, String type, String softwareVersion, String provider, String license, String origin,
            Instant acceptanceDate, String description) {
        this.name = name;
        this.type = type;
        this.softwareVersion = softwareVersion;
        this.provider = provider;
        this.license = license;
        this.origin = origin;
        this.acceptanceDate = acceptanceDate;
        this.description = description;
        this.versions = new ArrayList<>();
    }

    public void addVersion(SoftwareVersion version){
        versions.add(version);
    }
}
