package com.dogsi.itil.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SlaMetrics {
    
    private List<SlasByField> slasByManager;
    private List<SlasByField> slasByClient;
    private List<SlasByField> slasByService;
    private int crucialSlas;
}
