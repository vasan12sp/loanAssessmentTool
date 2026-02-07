package com.vasan.loanAssessmentTool.model;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Explanation {
    private String decisionStatement;
    private List<String> sections = new ArrayList<>();
    private List<String> supportingFactors = new ArrayList<>();
    private List<String> riskAreas = new ArrayList<>();
    private List<String> improvementTargets = new ArrayList<>();
    private String alternativeProducts;

    // Existing methods
    public void addSection(String line) {
        sections.add(line);
    }

    public void addSupportingFactor(String factor) {
        supportingFactors.add(factor);
    }

    public void addRiskArea(String risk) {
        riskAreas.add(risk);
    }

    public void addImprovementTarget(String target) {
        improvementTargets.add(target);
    }

    // Getters and setters for all fields
}
