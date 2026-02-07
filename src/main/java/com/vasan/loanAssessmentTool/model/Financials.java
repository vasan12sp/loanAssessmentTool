package com.vasan.loanAssessmentTool.model;

import lombok.Data;

@Data
public class Financials {
    // Input fields
    private double currentAssets;
    private double currentLiabilities;
    private double inventory;
    private double netOperatingIncome;
    private double annualDebtService;
    private double totalLiabilities;
    private double netWorth;
    private double netProfitAfterTax;
    private double netSales;
    private double ebit;
    private double interestExpense;
    private double projectedAnnualTurnover;
    private double digitalTransactionPercentage;
    private double grossOperatingIncome;
    private double totalOperatingExpenses;
    private double principalRepayment;
    private double interestRepayment;
    private double yoyGrowth;
    private double collateralValue;

    // Profitability trend
    private double npmCurrent;
    private double npmPrevious;

    // Calculated ratios
    private double currentRatio;
    private double quickRatio;
    private double dscr;
    private double debtToEquityRatio;
    private double netProfitMargin;
    private double interestCoverageRatio;
    private double roe;
    private double debtServiceRatio;

    // Working capital
    private double wcRequirement;
    private double borrowerMargin;
    private double bankContribution;
    private String wcCalculationMethod;

    // Status assessments
    private String liquidityStatus;
    private String workingCapital;
    private String liquidityRisk;
    private String quickRatioStatus;
    private String repaymentCapacity;
    private String approvalLikelihood;
    private String solvencyStatus;
    private String solvencyRisk;
    private String interestCoverageStatus;
    private String profitability;
    private String efficiency;
    private String profitabilityTrend;
    private boolean escalateRisk;

    // Risk assessment
    private String overallFinancialRisk;
    private String recommendation;
    private String combinedRiskAssessment;
    private String managementQuality;
    private String currentFinancialsStatus;

    // Thresholds
    private double dscrThreshold;
    private double deThreshold;

    // Flags
    private String assessmentMethod;
    private boolean standardTurnoverWaived;
}
