package com.vasan.loanAssessmentTool.Service;

import com.vasan.loanAssessmentTool.model.AssessmentResult;
import com.vasan.loanAssessmentTool.model.LoanApplication;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LoanServiceTest {

    @Autowired
    private LoanService loanService;

    @ParameterizedTest
    @CsvSource({
            // Scenario 1: Strong, Low-Risk MSME -> APPROVED
            "38,750,6,10,8,POSITIVE,25000000,4500000,2500000,1500000,4000000,2500000,2000000,4500000,23000000,1800000,6000000,3900000,REAL_PROPERTY,APPROVED",

            // Scenario 2: Good business but weak CIBIL -> CONDITIONAL_APPROVAL
            "42,650,7,12,10,STABLE,18000000,3600000,2200000,1200000,3000000,2100000,2400000,4000000,17000000,1200000,5000000,3000000,REAL_PROPERTY,CONDITIONAL_APPROVAL",

            // Scenario 3: Strong financials but low collateral -> REJECTED
            "35,740,5,9,7,POSITIVE,30000000,5500000,2500000,800000,5000000,2500000,2100000,6000000,28000000,2200000,10000000,3000000,REAL_PROPERTY,REJECTED",

            // Scenario 4: High turnover but poor cash flow -> REJECTED
            "40,720,8,15,12,STABLE,50000000,6000000,5500000,2000000,1800000,2000000,4000000,5000000,48000000,800000,8000000,4500000,EQUIPMENT,REJECTED",

            // Scenario 5: New MSME -> CONDITIONAL_APPROVAL
            "33,730,1,4,3,NEUTRAL,6000000,1500000,1100000,400000,800000,600000,900000,1800000,5800000,350000,2500000,1500000,INVENTORY,CONDITIONAL_APPROVAL",

            // Scenario 6: Over-leveraged business -> REJECTED
            "45,710,6,14,11,STABLE,20000000,4200000,2800000,900000,2400000,2000000,5000000,1800000,19000000,900000,7000000,5000000,REAL_PROPERTY,REJECTED"
    })
    void testRealWorldMSMEScenarios(
            int age,
            int cibilScore,
            int businessVintage,
            int promoterExperience,
            int promoterIndustryExperience,
            String industryOutlook,

            double annualTurnover,
            double currentAssets,
            double currentLiabilities,
            double inventory,
            double netOperatingIncome,
            double annualDebtService,
            double totalLiabilities,
            double netWorth,
            double netSales,
            double netProfitAfterTax,

            double requestedLoanAmount,
            double collateralValue,
            String assetType,

            String expectedDecision
    ) {

        // ---- Build LoanApplication exactly as your API would ----
        LoanApplication application = new LoanApplication();

        // Applicant profile
        application.setAge(age);
        application.setCibilScore(cibilScore);
        application.setBusinessVintage(businessVintage);
        application.setPromoterExperience(promoterExperience);
        application.setPromoterIndustryExperience(promoterIndustryExperience);
        application.setIndustryOutlook(industryOutlook);

        // Financials
        application.setAnnualTurnover(annualTurnover);
        application.setCurrentAssets(currentAssets);
        application.setCurrentLiabilities(currentLiabilities);
        application.setInventory(inventory);
        application.setNetOperatingIncome(netOperatingIncome);
        application.setAnnualDebtService(annualDebtService);
        application.setTotalLiabilities(totalLiabilities);
        application.setNetWorth(netWorth);
        application.setNetSales(netSales);
        application.setNetProfitAfterTax(netProfitAfterTax);

        // Loan + Collateral
        application.setRequestedLoanAmount(requestedLoanAmount);
        application.setCollateralValue(collateralValue);
        application.setAssetType(assetType);

        // ---- Call your real service (Drools rules will run) ----
        AssessmentResult result = loanService.assessLoan(application);

        // ---- Assert the final decision ----
        assertEquals(expectedDecision, result.getDecision(),
                "Decision mismatch for scenario with CIBIL=" + cibilScore +
                        " and collateral=" + collateralValue);

        if ("APPROVED".equals(expectedDecision)) {
            assertEquals("LOW", result.getDisplayRiskLevel());
        }

    }
}
