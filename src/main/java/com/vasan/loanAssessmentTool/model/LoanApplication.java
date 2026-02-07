package com.vasan.loanAssessmentTool.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanApplication {
    // Applicant Profile
    private int age;
    private int cibilScore;
    private int businessVintage;
    private int promoterExperience;
    private int promoterIndustryExperience;
    private String industryOutlook;

    // Financial Metrics
    private double annualTurnover;
    private double currentAssets;
    private double currentLiabilities;
    private double inventory;
    private double netOperatingIncome;
    private double annualDebtService;
    private double totalLiabilities;
    private double netWorth;
    private double netSales;
    private double netProfitAfterTax;

    // Loan Request
    private double requestedLoanAmount;
    private double collateralValue;
    private String assetType;
}
