package com.dogsi.itil.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeMetricsDto {

    private long totalChanges;
    private Long notTakenChanges;
    private float averageIncidents;
    private float averageProblems;
    private Long cancelledChanges;
    private List<ItemByField> changesByCategory;
    private List<ItemByPriority> changesByPriority;
    private List<ItemByDay> changesByDay;


    
}
