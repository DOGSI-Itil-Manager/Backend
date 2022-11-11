package com.dogsi.itil.dto;

import java.time.*;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.dogsi.itil.domain.incident.enums.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProblemDto {

    private @NotBlank String name;
    private @NotBlank String category;
    private @NotNull Priority priority;
    private @NotNull Impact impact;
    private @NotNull State state;
    private String description;
    private @NotNull Instant reportedDate;
    private Date closedDate;
    private List<Long> incidentIds;
    private String emailOfUserInCharge;
}
