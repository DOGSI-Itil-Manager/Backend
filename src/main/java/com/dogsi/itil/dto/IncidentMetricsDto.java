package com.dogsi.itil.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IncidentMetricsDto {
    long total;
    long notTaken;
    List<ItemByPriority> byPriority;
    List<ItemByDay> byDay;
    List<ItemBySatisfaction> bySatisfaction;
    List<ItemByField> byCategory;
}
