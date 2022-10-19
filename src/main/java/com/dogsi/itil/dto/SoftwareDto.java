package com.dogsi.itil.dto;

import java.time.Instant;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SoftwareDto {

    private @NotBlank String name;
    private @NotBlank String type;
    private String version;
    private @NotBlank String provider;
    private String license;
    private String origin;
    private @NotNull Instant acceptanceDate;
    private String description;  
}