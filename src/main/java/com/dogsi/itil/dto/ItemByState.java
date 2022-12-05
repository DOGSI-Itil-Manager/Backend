package com.dogsi.itil.dto;

import com.dogsi.itil.domain.State;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ItemByState {
    private State field;
    private long count;
}
