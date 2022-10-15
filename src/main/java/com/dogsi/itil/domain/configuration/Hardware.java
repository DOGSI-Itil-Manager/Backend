package com.dogsi.itil.domain.configuration;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="hardware")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Hardware {

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="hardware_id")
    private Long id;

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
    private Float capacity; // De que es la capacidad

    @Column
    private String description;

    @Builder
    public Hardware(String name, String type, String serialNumber, String location, String provider, Float price,
            Instant additionDate, Float capacity, String description) {
        this.name = name;
        this.type = type;
        this.serialNumber = serialNumber;
        this.location = location;
        this.provider = provider;
        this.price = price;
        this.additionDate = additionDate;
        this.capacity = capacity;
        this.description = description;
    }
}