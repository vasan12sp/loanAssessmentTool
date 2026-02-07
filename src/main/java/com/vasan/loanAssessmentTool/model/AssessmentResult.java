package com.vasan.loanAssessmentTool.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class AssessmentResult {
    private String decision;
    private String riskLevel;
    private String primaryRiskCategory;  // Single source of truth
    private double dscr;
    private double currentRatio;

    // Structured explanations
    private String decisionStatement;
    private List<String> supportingFactors;
    private List<String> riskAreas;
    private List<String> improvementTargets;
    private String nextSteps;
    private String alternativeProducts;
    private List<String> explanations;

    // Computed display method
    public String getDisplayRiskLevel() {
        if (primaryRiskCategory != null) {
            return primaryRiskCategory;
        }
        return riskLevel;
    }
}
