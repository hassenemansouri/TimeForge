package tn.esprit.user_strategicpartership.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import java.io.IOException;
import tn.esprit.user_strategicpartership.dto.ResponsePayment;
import tn.esprit.user_strategicpartership.service.PayementService;
@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequiredArgsConstructor
public class PayementController {

  private final PayementService payementService;

  @GetMapping("/payment/success")
  public String paymentSuccess(@RequestParam("payment_id") String paymentId) throws IOException {
    boolean verifPayment = payementService.verifyPayment(paymentId);
    if (verifPayment) {
      return "paymentSuccess";
    }else{
      return "paymentError";
    }
  }

  @GetMapping("/")
  public String index() {
    return "index";
  }

  @GetMapping("/payment/error")
  public String paymentError(){
    return "paymentError";
  }

  @PostMapping("/payment/create")
  @ResponseBody
  public ResponsePayment createPayment(@RequestParam("amount") Integer amount) throws IOException {
    return payementService.generatePayment(amount);
  }

}
