package com.vasan.loanAssessmentTool.model;

import lombok.Data;

@Data
public class RiskScoringModel {
    private double weightedRiskScore;
    private double dscrScore;
    private double liquidityScore;
    private double managementScore;
    private double collateralScore;
}
