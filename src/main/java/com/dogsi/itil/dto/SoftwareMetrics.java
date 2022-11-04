package com.dogsi.itil.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SoftwareMetrics {

    private List<ItemByField> softwareByType;
    private long totalSoftware;
    private float changeSpeed;
    
}
