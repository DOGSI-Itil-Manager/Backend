package com.dogsi.itil.dto;

import com.dogsi.itil.domain.Priority;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ItemByPriority {
    private Priority field;
    private long count;
}
