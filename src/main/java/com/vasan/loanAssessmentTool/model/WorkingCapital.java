package com.vasan.loanAssessmentTool.model;

import lombok.Data;

@Data
public class WorkingCapital {
    private double daysInventoryOutstanding;
    private double daysSalesOutstanding;
    private double daysPayableOutstanding;
    private double operatingCycleDays;
    private String wcEligibility;
    private boolean justificationNeeded;
}
