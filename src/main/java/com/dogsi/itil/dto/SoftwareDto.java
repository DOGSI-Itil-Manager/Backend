package com.dogsi.itil.dto;

import java.time.Instant;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SoftwareDto {

    private @NotBlank String name;
    private @NotBlank String type;
    private String softwareVersion;
    private @NotBlank String provider;
    private String license;
    private String origin;
    private @NotNull Instant acceptanceDate;
    private String description;  
}
