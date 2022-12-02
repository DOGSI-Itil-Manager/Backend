package com.dogsi.itil.dto;

import com.dogsi.itil.domain.Satisfaction;


import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class ItemBySatisfaction {
    private String field;
    private long count;

    public ItemBySatisfaction(Satisfaction field, long count) {
        this.field = field == null ? "SIN_CALIFICAR" : field.name();
        this.count = count;
    }
}
