package com.vasan.loanAssessmentTool.model;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Explanation {
    private List<String> sections = new ArrayList<>();
    private List<String> supportingFactors = new ArrayList<>();
    private List<String> riskAreas = new ArrayList<>();
    private List<String> improvementTargets = new ArrayList<>();
    private List<String> alternativeProducts = new ArrayList<>();
    private String decisionStatement;
    private String alternativeProductsText;

    public void addSection(String section) {
        this.sections.add(section);
    }

    public void addSupportingFactor(String factor) {
        this.supportingFactors.add(factor);
    }

    public void addRiskArea(String risk) {
        this.riskAreas.add(risk);
    }

    public void addImprovementTarget(String target) {
        this.improvementTargets.add(target);
    }

    public void addAlternativeProduct(String product) {
        this.alternativeProducts.add(product);
    }

    public void setAlternativeProducts(String productsText) {
        this.alternativeProductsText = productsText;
    }
}
