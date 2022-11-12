package com.dogsi.itil.dto;

import java.time.*;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.dogsi.itil.domain.Impact;
import com.dogsi.itil.domain.Priority;
import com.dogsi.itil.domain.Satisfaction;
import com.dogsi.itil.domain.State;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IncidentDto {

    private @NotBlank String name;
    private @NotBlank String category;
    private @NotNull Priority priority;
    private @NotNull Impact impact;
    private @NotNull State state;
    private String assignee;
    private String description;
    private @NotNull Instant reportedDate;
    private Date closedDate;
    private Satisfaction satisfaction;
}
