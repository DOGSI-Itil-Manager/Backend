package com.dogsi.itil.dto;

import java.time.Instant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SolutionDto {
    private @NotBlank String name;
    private @NotNull Instant creationDate;
    private @NotNull Long knownErrorId;  
}
