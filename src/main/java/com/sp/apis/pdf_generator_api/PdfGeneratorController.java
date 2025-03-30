package com.sp.apis.pdf_generator_api;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.responses.*;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.io.*;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "PDF API", description = "Generate PDFs from text")
public class PdfController {

  @Operation(summary = "Generate PDF")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "PDF generated"),
      @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @PostMapping(value = "/generate", produces = MediaType.APPLICATION_PDF_VALUE)
  public ResponseEntity<byte[]> generatePdf(
      @RequestBody String text,
      @RequestParam(defaultValue = "document") String filename) {

    try (PDDocument doc = new PDDocument()) {
      PDPage page = new PDPage();
      doc.addPage(page);

      try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {
        cs.beginText();
        cs.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
        cs.newLineAtOffset(50, 700);
        cs.showText(text);
        cs.endText();
      }

      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      doc.save(baos);

      return ResponseEntity.ok()
          .header("Content-Disposition", "attachment; filename=" + filename + ".pdf")
          .body(baos.toByteArray());
    } catch (IOException e) {
      throw new RuntimeException("PDF generation failed", e);
    }
  }
}