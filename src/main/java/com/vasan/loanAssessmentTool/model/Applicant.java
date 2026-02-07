package com.vasan.loanAssessmentTool.model;

import lombok.Data;

@Data
public class Applicant {
    private String name;
    private int age;
    private String businessType;
    private double turnover;
    private int cibilScore;
    private int promoterExperience;
    private int promoterIndustryExperience;
    private int businessVintage;
    private boolean priorSuccess;
    private boolean priorDefault;
    private int priorDefaults;
    private String paymentHistory;
    private int recoveryPeriodMonths;
    private double loanAmount;
    private double collateralCoverage;
    private String industryOutlook;

    // Eligibility flags
    private boolean eligible = true;
    private boolean eligibleAge;
    private boolean eligibleCibil;
    private boolean eligibleVintage;
    private String rejectionReason;
    private String cibilCategory;

    // Management assessment
    private String managementQuality;
    private String managementRisk;
    private String trackRecord;

    // Decision outputs
    private String finalDecision;
    private String riskCategory;
    private String weightedRiskCategory;
    private String approvalPath;
    private String documentation;
    private double interestAdjustment;
    private double loanAmountAdjustment;
    private String disbursalMethod;
    private String recommendation;
    private boolean specialistReviewRequired;

    // Exception handling
    private boolean applyModifiedThresholds;
    private double collateralRequirement;
    private boolean reassessFromCurrent;
    private int probationPeriod;
    private boolean riskMitigationAdequate;
    private boolean sustainableGrowthEvidence;

    // Scoring
    private double dscr;
}
