package tn.esprit.user_strategicpartership.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequest {
  @NotBlank
  private String orderId;

  @NotNull
  @DecimalMin(value = "1.0")
  private BigDecimal amount;

  @NotBlank
  @Pattern(regexp = "^[A-Z]{3}$")
  private String currency = "TND";

  @NotBlank
  @Size(min = 2, max = 50)
  private String firstName;

  @NotBlank
  @Size(min = 2, max = 50)
  private String lastName;

  @NotBlank
  @Email
  private String email;

  @NotBlank
  @Pattern(regexp = "^\\+?[0-9\\s]{8,20}$")
  private String phone;

  @NotBlank
  @Size(max = 255)
  private String note;
}