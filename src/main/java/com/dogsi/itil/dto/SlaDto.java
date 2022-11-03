package com.dogsi.itil.dto;

import java.time.Instant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.dogsi.itil.domain.SLASide;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SlaDto {

    private @NotBlank String name;
    private @NotBlank String service;
    private @NotNull Boolean crucial;
    private String manager;
    private @NotBlank String sideName;
    private @NotNull SLASide sideType;
    private @NotNull Instant startDate;
    private @NotNull Instant endDate;
    private String description;
}
