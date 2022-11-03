package com.dogsi.itil.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HardwareMetrics {

    private float totalSpent;
    private List<ItemByField> hardwareByType;
    private Long totalHardware;
    private float changeSpeed;
}
