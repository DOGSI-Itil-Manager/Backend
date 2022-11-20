package com.dogsi.itil.dto;

import com.dogsi.itil.domain.Satisfaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ItemBySatisfaction {
    private Satisfaction field;
    private long count;
}
