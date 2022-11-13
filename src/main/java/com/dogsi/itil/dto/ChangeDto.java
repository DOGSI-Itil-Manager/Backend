package com.dogsi.itil.dto;


import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.dogsi.itil.domain.Impact;
import com.dogsi.itil.domain.Priority;
import com.dogsi.itil.domain.State;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeDto {

    private @NotBlank String name;

    private @NotBlank String category;
    
    private @NotNull Priority priority;

    private @NotNull Impact impact;

    private String description;

    private Date closedDate;

    private @NotNull State state;

    private List<Long> incidentIds;

    private List<Long> problemIds;

    private String emailOfUserInCharge;
}
