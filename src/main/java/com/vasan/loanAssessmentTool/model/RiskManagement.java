package com.vasan.loanAssessmentTool.model;

import lombok.Data;
import java.util.List;
import java.util.ArrayList;

@Data
public class RiskManagement {
    private String riskCategory;
    private List<String> covenants = new ArrayList<>();
    private String monitoringFrequency;
    private boolean guaranteeRequired;
    private boolean enhancedMonitoring;
}
