package com.dogsi.itil.dto;

import java.time.*;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KnownErrorDto {
    private @NotBlank String name;
    private @NotBlank String category;
    private String description;
    private @NotNull Instant creationDate;
    private List<Long> problems;
    private String rootcause;
    private @NotNull List<Long> solutions;
}