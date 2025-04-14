package tn.esprit.user_strategicpartership.service;



import brevo.ApiClient;
import brevo.ApiException;
import brevo.Configuration;
import brevoApi.TransactionalEmailsApi;
import brevoModel.CreateSmtpEmail;
import brevoModel.SendSmtpEmail;
import brevoModel.SendSmtpEmailSender;
import brevoModel.SendSmtpEmailTo;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class BrevoEmailService {

  @Value("${brevo.api-key}")
  private String apiKey;

  @Value("${brevo.sender-email}")
  private String senderEmail;

  @Value("${brevo.sender-name}")
  private String senderName;

  public void sendEmail(String toEmail, String subject, String htmlContent) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setApiKey(apiKey);

    TransactionalEmailsApi apiInstance = new TransactionalEmailsApi(defaultClient);

    SendSmtpEmailSender sender = new SendSmtpEmailSender();
    sender.setEmail(senderEmail);
    sender.setName(senderName);

    SendSmtpEmailTo to = new SendSmtpEmailTo();
    to.setEmail(toEmail);

    SendSmtpEmail email = new SendSmtpEmail();
    email.setSender(sender);
    email.setTo(List.of(to));
    email.setSubject(subject);
    email.setHtmlContent(htmlContent);

    try {
      CreateSmtpEmail response = apiInstance.sendTransacEmail(email);
      System.out.println("Email sent! Message ID: " + response.getMessageId());
    } catch (ApiException e) {
      System.err.println("Exception when calling Brevo API");
      e.printStackTrace();
    }
  }
}
