package com.hacker.trading.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hacker.trading.entities.PaymentOrder;
import com.hacker.trading.entities.User;
import com.hacker.trading.response.PaymentResponse;
import com.hacker.trading.service.PaymentService;
import com.hacker.trading.service.UserService;
import com.hacker.trading.domain.PaymentMethod;

@RestController
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    private UserService userService;

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/api/payment/{paymentMethod}/amount/{amount}")
    public ResponseEntity<PaymentResponse> paymentHandler(
        @PathVariable PaymentMethod paymentMethod,
        @PathVariable Long amount,
        @RequestHeader("Authorization")String jwt) throws Exception{
            User user = userService.findUserProfileByJwt(jwt);
            PaymentResponse paymentResponse;
            PaymentOrder order = paymentService.createOrder(user, amount, paymentMethod);
            if(paymentMethod.equals(PaymentMethod.RAZORPAY)){
                paymentResponse = paymentService.createRazorpayPaymentLink(user, amount);
            }
            else{
                paymentResponse = paymentService.createStripePaymentLink(user, amount, order.getId());
            }
            return new ResponseEntity<>(paymentResponse, HttpStatus.CREATED);
        }
    
}
