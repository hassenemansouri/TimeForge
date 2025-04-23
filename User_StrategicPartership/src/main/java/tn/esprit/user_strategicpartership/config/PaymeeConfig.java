package tn.esprit.user_strategicpartership.config;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;



@Data
@Validated
@Configuration
@ConfigurationProperties(prefix = "paymee")
public class PaymeeConfig {
  @NotNull
  private Api api;
  @NotNull private Urls urls;
  @NotNull private TestCredentials test;

  @Data
  public static class Api {
    @NotBlank
    private String url;
    @NotBlank private String token;
    @NotNull private Long redirectTimeout;
  }

  @Data
  public static class Urls {
    @NotBlank private String returnUrl;
    @NotBlank private String cancelUrl;
    @NotBlank private String webhookUrl;
  }

  @Data
  public static class TestCredentials {
    private String phone;
    private String password;
  }
}