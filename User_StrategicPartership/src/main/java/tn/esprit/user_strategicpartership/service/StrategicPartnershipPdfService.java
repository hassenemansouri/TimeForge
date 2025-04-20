package tn.esprit.user_strategicpartership.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.qrcode.*;
import org.springframework.stereotype.Service;
import tn.esprit.user_strategicpartership.entity.StrategicPartnertship;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

@Service
public class StrategicPartnershipPdfService {

  private static final Font TITLE_FONT = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
  private static final Font HEADER_FONT = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
  private static final Font NORMAL_FONT = new Font(Font.FontFamily.HELVETICA, 10);

  public byte[] generatePartnershipPdf(StrategicPartnertship partnership) throws DocumentException, IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    Document document = new Document();
    PdfWriter.getInstance(document, baos);

    document.open();
    addMetaData(document);
    addTitle(document);
    addPartnershipDetails(document, partnership);
    addParticipants(document, partnership);
    addBlockchainInfo(document, partnership);
    addQrCode(document, partnership);
    document.close();

    return baos.toByteArray();
  }

  private void addMetaData(Document document) {
    document.addTitle("Strategic Partnership Agreement");
    document.addSubject("Official partnership document");
    document.addKeywords("partnership, blockchain, agreement");
    document.addAuthor("Your Organization");
    document.addCreator("Strategic Partnership System");
  }

  private void addTitle(Document document) throws DocumentException {
    Paragraph title = new Paragraph("STRATEGIC PARTNERSHIP AGREEMENT", TITLE_FONT);
    title.setAlignment(Element.ALIGN_CENTER);
    title.setSpacingAfter(20f);
    document.add(title);
  }

  private void addPartnershipDetails(Document document, StrategicPartnertship partnership) throws DocumentException {
    Paragraph detailsHeader = new Paragraph("Partnership Details:", HEADER_FONT);
    detailsHeader.setSpacingAfter(10f);
    document.add(detailsHeader);

    PdfPTable table = new PdfPTable(2);
    table.setWidthPercentage(100);
    table.setSpacingBefore(10f);

    addTableRow(table, "Name:", partnership.getName());
    addTableRow(table, "Description:", partnership.getDescription());
    addTableRow(table, "Creation Date:",
        partnership.getCreationDate().format(DateTimeFormatter.ISO_LOCAL_DATE));

    document.add(table);
  }

  private void addParticipants(Document document, StrategicPartnertship partnership) throws DocumentException {
    Paragraph participantsHeader = new Paragraph("Participants:", HEADER_FONT);
    participantsHeader.setSpacingBefore(15f);
    participantsHeader.setSpacingAfter(10f);
    document.add(participantsHeader);

    List list = new List(List.ORDERED);
    partnership.getParticipants().forEach(list::add);
    document.add(list);
  }

  private void addBlockchainInfo(Document document, StrategicPartnertship partnership) throws DocumentException {
    Paragraph blockchainHeader = new Paragraph("Blockchain Verification:", HEADER_FONT);
    blockchainHeader.setSpacingBefore(15f);
    document.add(blockchainHeader);

    PdfPTable table = new PdfPTable(2);
    table.setWidthPercentage(100);
    table.setSpacingBefore(10f);

    addTableRow(table, "Blockchain Hash:", partnership.getBlockchainHash());
    addTableRow(table, "Registration Date:",
        partnership.getBlockchainTimestamp().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

    document.add(table);
  }

  private void addQrCode(Document document, StrategicPartnertship partnership) throws DocumentException, IOException {
    Paragraph qrHeader = new Paragraph("Verification QR Code:", HEADER_FONT);
    qrHeader.setSpacingBefore(20f);
    document.add(qrHeader);

    String verificationUrl = generateVerificationUrl(partnership);
    BarcodeQRCode qrCode = new BarcodeQRCode(verificationUrl, 150, 150, null);
    Image qrImage = qrCode.getImage();
    qrImage.setAlignment(Element.ALIGN_CENTER);
    document.add(qrImage);

    Paragraph scanText = new Paragraph("Scan this code to verify the partnership", NORMAL_FONT);
    scanText.setAlignment(Element.ALIGN_CENTER);
    scanText.setSpacingBefore(10f);
    document.add(scanText);
  }

  private String generateVerificationUrl(StrategicPartnertship partnership) {
    return "https://yourdomain.com/verify-partnership?id=" + partnership.getId()
        + "&hash=" + partnership.getBlockchainHash();
  }

  private void addTableRow(PdfPTable table, String key, String value) {
    table.addCell(new Phrase(key, HEADER_FONT));
    table.addCell(new Phrase(value, NORMAL_FONT));
  }
}