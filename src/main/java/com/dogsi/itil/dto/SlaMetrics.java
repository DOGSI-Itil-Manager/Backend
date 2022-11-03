package com.dogsi.itil.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SlaMetrics {
    
    private List<ItemByField> slasByManager;
    private List<ItemByField> slasByClient;
    private List<ItemByField> slasByProvider;
    private List<ItemByField> slasByService;
    private int crucialSlas;
    private Long totalSlas;
    private float slaChangeSpeed;
}
