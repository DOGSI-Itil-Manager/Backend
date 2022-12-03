package com.dogsi.itil.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProblemMetricsDto {
    List<ItemByField> countByUserInCharge;
    List<ItemByPriority> byPriority;
    List<ItemByDay> byDay;
    List<ItemByField> byCategory;
}
