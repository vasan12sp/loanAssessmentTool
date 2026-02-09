package com.vasan.loanAssessmentTool.Controller;

import com.itextpdf.text.DocumentException;
import com.vasan.loanAssessmentTool.Service.AssessmentPdfService;
import com.vasan.loanAssessmentTool.Service.LoanService;
import com.vasan.loanAssessmentTool.model.AssessmentResult;
import com.vasan.loanAssessmentTool.model.LoanApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("result")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @Autowired
    private AssessmentPdfService assessmentPdfService;

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
        model.addAttribute("application", application);
        return "result";
    }

    @PostMapping("/download-result")
    public ResponseEntity<byte[]> downloadResult(@ModelAttribute("result") AssessmentResult result,
                                                 SessionStatus sessionStatus) {
        try {
            byte[] pdfBytes = assessmentPdfService.generatePdf(result);

            // Clear the session after generating PDF
            sessionStatus.setComplete();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(
                    ContentDisposition.attachment()
                            .filename("loan_assessment_" + System.currentTimeMillis() + ".pdf")
                            .build()
            );

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);
        } catch (DocumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
