package tn.esprit.user_strategicpartership.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import tn.esprit.user_strategicpartership.entity.StrategicPartnertship;

import tn.esprit.user_strategicpartership.service.StrategicPartnershipPdfService;
import tn.esprit.user_strategicpartership.service.StrategicPartnershipService;

@RestController
@RequestMapping("/api/partnerships")
public class PdfController {

  @Autowired
  private StrategicPartnershipPdfService pdfService;

  @Autowired
  private StrategicPartnershipService partnershipService;

  @GetMapping("/{id}/pdf")
  public ResponseEntity<byte[]> generatePdf(@PathVariable String id) {
    try {
      StrategicPartnertship partnership = partnershipService.getPartnership(id);
      byte[] pdf = pdfService.generatePartnershipPdf(partnership);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_PDF);
      headers.setContentDisposition(
          ContentDisposition.builder("attachment")
              .filename("partnership_" + id + ".pdf")
              .build());
      headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

      return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

}