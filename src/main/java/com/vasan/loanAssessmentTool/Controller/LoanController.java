package com.vasan.loanAssessmentTool.Controller;

import com.vasan.loanAssessmentTool.Service.LoanService;
import com.vasan.loanAssessmentTool.model.AssessmentResult;
import com.vasan.loanAssessmentTool.model.LoanApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping("/")
    public String showHome() {
        return "home";
    }

    @GetMapping("/assessment-form")
    public String showForm(Model model) {
        model.addAttribute("application", new LoanApplication());
        return "index";
    }

    @PostMapping("/assess")
    public String assessLoan(@ModelAttribute LoanApplication application, Model model) {
        AssessmentResult result = loanService.assessLoan(application);
        model.addAttribute("result", result);
        model.addAttribute("application", application); // Pass back to show inputs if needed
        return "result";
    }
}
