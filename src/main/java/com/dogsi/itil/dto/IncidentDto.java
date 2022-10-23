package com.dogsi.itil.dto;

import java.time.*;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IncidentDto {

    private @NotBlank String name;
    private @NotBlank String category;
    private @NotBlank String priority;
    private @NotBlank String impact;
    private @NotBlank String state;
    private @NotBlank String assignee;
    private String description;
    private @NotNull Instant reportedDate;
    private Date closedDate;
}
