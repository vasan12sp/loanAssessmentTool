package com.vasan.loanAssessmentTool.Service;

import com.vasan.loanAssessmentTool.model.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

    @Autowired
    private KieContainer kieContainer;

    public AssessmentResult assessLoan(LoanApplication application) {
        KieSession kieSession = kieContainer.newKieSession();

        // Create objects from application
        Applicant applicant = new Applicant();
        applicant.setAge(application.getAge());
        applicant.setCibilScore(application.getCibilScore());
        applicant.setBusinessVintage(application.getBusinessVintage());
        applicant.setPromoterExperience(application.getPromoterExperience());
        applicant.setPromoterIndustryExperience(application.getPromoterIndustryExperience());
        applicant.setLoanAmount(application.getRequestedLoanAmount());
        applicant.setIndustryOutlook(application.getIndustryOutlook());

        Financials financials = new Financials();
        financials.setCurrentAssets(application.getCurrentAssets());
        financials.setCurrentLiabilities(application.getCurrentLiabilities());
        financials.setInventory(application.getInventory());
        financials.setNetOperatingIncome(application.getNetOperatingIncome());
        financials.setAnnualDebtService(application.getAnnualDebtService());
        financials.setTotalLiabilities(application.getTotalLiabilities());
        financials.setNetWorth(application.getNetWorth());
        financials.setProjectedAnnualTurnover(application.getAnnualTurnover());
        financials.setNetSales(application.getNetSales());
        financials.setNetProfitAfterTax(application.getNetProfitAfterTax());


        Collateral collateral = new Collateral();
        collateral.setCollateralValue(application.getCollateralValue());
        collateral.setLoanAmount(application.getRequestedLoanAmount());
        collateral.setAssetType(application.getAssetType());

// Set MAST criteria based on asset type
        if ("REAL_PROPERTY".equals(application.getAssetType())) {
            collateral.setMarketable(true);
            collateral.setAscertainable(true);
            collateral.setStable(true);
            collateral.setTransferable(true);
        } else if ("EQUIPMENT".equals(application.getAssetType())) {
            collateral.setMarketable(true);
            collateral.setAscertainable(true);
            collateral.setStable(false);
            collateral.setTransferable(true);
        } else if ("INVENTORY".equals(application.getAssetType())) {
            collateral.setMarketable(true);
            collateral.setAscertainable(false);
            collateral.setStable(false);
            collateral.setTransferable(true);
        }

        if (application.getCollateralValue() > 0 && application.getRequestedLoanAmount() > 0) {
            collateral.setLtvRatio(application.getRequestedLoanAmount() / application.getCollateralValue());
            collateral.setCollateralCoverage(application.getCollateralValue() / application.getRequestedLoanAmount());
            applicant.setCollateralCoverage(collateral.getCollateralCoverage());
        }


        Explanation explanation = new Explanation();
        RiskScoringModel riskScoringModel = new RiskScoringModel();
        RiskManagement riskManagement = new RiskManagement();

        kieSession.insert(applicant);
        kieSession.insert(financials);
        kieSession.insert(collateral);
        kieSession.insert(explanation);
        kieSession.insert(riskScoringModel);
        kieSession.insert(riskManagement);

        int rulesFired = kieSession.fireAllRules();
        System.out.println("Rules fired: " + rulesFired);

        kieSession.dispose();

// Build result from modified objects
        AssessmentResult result = new AssessmentResult();
        result.setDecision(applicant.getFinalDecision());
        result.setRiskLevel(applicant.getRiskCategory());
        result.setPrimaryRiskCategory(applicant.getRiskCategory());

        if (financials.getOverallFinancialRisk() != null &&
                !financials.getOverallFinancialRisk().equals(applicant.getRiskCategory())) {
            System.out.println("WARNING: Risk category mismatch - using final decision category");
        }

        result.setDscr(financials.getDscr());
        result.setCurrentRatio(financials.getCurrentRatio());

// Transfer structured explanations
        result.setDecisionStatement(explanation.getDecisionStatement());
        result.setSupportingFactors(explanation.getSupportingFactors());
        result.setRiskAreas(explanation.getRiskAreas());
        result.setImprovementTargets(explanation.getImprovementTargets());
        result.setAlternativeProducts(explanation.getAlternativeProducts());
        result.setExplanations(explanation.getSections());

        return result;

    }

}
