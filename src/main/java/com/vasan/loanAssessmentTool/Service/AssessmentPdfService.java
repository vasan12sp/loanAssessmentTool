package com.vasan.loanAssessmentTool.Service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.vasan.loanAssessmentTool.model.AssessmentResult;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class AssessmentPdfService {

    public byte[] generatePdf(AssessmentResult result) throws DocumentException {
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PdfWriter.getInstance(document, baos);
        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.BLUE);
        Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);

        // Title
        Paragraph title = new Paragraph("MSME Loan Assessment Report", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(10);
        document.add(title);

        // Date
        Paragraph date = new Paragraph("Generated: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm")), normalFont);
        date.setAlignment(Element.ALIGN_RIGHT);
        date.setSpacingAfter(20);
        document.add(date);

        // Decision
        Paragraph decision = new Paragraph("Decision: " + result.getDecision(), headerFont);
        decision.setSpacingAfter(10);
        document.add(decision);document.add(new Paragraph(result.getDecisionStatement(), normalFont));
        document.add(Chunk.NEWLINE);

        // Metrics
        addSection(document, "Calculated Metrics", headerFont);
        document.add(new Paragraph("DSCR: " + String.format("%.2f", result.getDscr()), normalFont));
        document.add(new Paragraph("Current Ratio: " + String.format("%.2f", result.getCurrentRatio()), normalFont));
        document.add(new Paragraph("Risk Level: " + result.getDisplayRiskLevel(), normalFont));
        document.add(Chunk.NEWLINE);

        // Supporting Factors
        if (result.getSupportingFactors() != null && !result.getSupportingFactors().isEmpty()) {
            addSection(document, "Strengths in Application", headerFont);
            for (String factor : result.getSupportingFactors()) {
                document.add(new Paragraph("• " + factor, normalFont));
            }
            document.add(Chunk.NEWLINE);
        }

        // Risk Areas
        if (result.getRiskAreas() != null && !result.getRiskAreas().isEmpty()) {
            addSection(document, "Areas of Concern", headerFont);
            for (String risk : result.getRiskAreas()) {
                document.add(new Paragraph("• " + risk, normalFont));
            }
            document.add(Chunk.NEWLINE);
        }

        // Improvement Targets
        if (result.getImprovementTargets() != null && !result.getImprovementTargets().isEmpty()) {
            addSection(document, "Recommended Improvements", headerFont);
            for (String target : result.getImprovementTargets()) {
                document.add(new Paragraph("• " + target, normalFont));
            }
            document.add(Chunk.NEWLINE);
        }

        // Alternative Products
        if (result.getAlternativeProducts() != null && !result.getAlternativeProducts().isEmpty()) {
            addSection(document, "Alternative Financing Options", headerFont);
            for (String product : result.getAlternativeProducts()) {
                document.add(new Paragraph("• " + product, normalFont));
            }
        }

        document.close();
        return baos.toByteArray();
    }

    private void addSection(Document document, String title, Font font) throws DocumentException {
        Paragraph section = new Paragraph(title, font);
        section.setSpacingBefore(10);
        section.setSpacingAfter(5);
        document.add(section);
    }
}
