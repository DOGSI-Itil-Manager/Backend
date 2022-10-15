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
@Table(name="sla")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class SLA {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="software_id")
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
    private String client;

    @Column(nullable = false)
    private Instant startDate;

    @Column(nullable = false)
    private Instant endDate;

    @Column
    private String description;

    @Builder
    public SLA(String name, String service, boolean crucial, String manager, String client, Instant startDate,
            Instant endDate, String description) {
        this.name = name;
        this.service = service;
        this.crucial = crucial;
        this.manager = manager;
        this.client = client;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }
}
