package com.dogsi.itil.dto;

import java.time.Instant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HardwareDto {

    private @NotBlank String name;
    private @NotBlank String type;
    private @NotBlank String serialNumber;
    private @NotBlank String location;
    private @NotBlank String provider;
    private @NotNull Float price;
    private @NotNull Instant additionDate;
    private String description;
}
