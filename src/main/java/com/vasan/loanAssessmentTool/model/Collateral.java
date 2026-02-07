package com.vasan.loanAssessmentTool.model;

import lombok.Data;

@Data
public class Collateral {
    private String assetType;
    private String mastScore;
    private boolean valuationCurrent;
    private double ltvRatio;
    private String condition;
    private String marketDemand;
    private double collateralValue;
    private double loanAmount;
    private double collateralCoverage;

    // MAST criteria
    private boolean marketable;
    private boolean ascertainable;
    private boolean stable;
    private boolean transferable;

    // Assessment outputs
    private String collateralQuality;
    private double ltvApproved;
    private String collateralAdequacy;
    private boolean additionalSecurityRequired;
    private boolean collateralMitigatesRisk;
    private boolean collateralInsufficient;
    private String mastValidation;
}
