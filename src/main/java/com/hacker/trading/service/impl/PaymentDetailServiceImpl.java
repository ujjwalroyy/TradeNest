package com.hacker.trading.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hacker.trading.entities.PaymentDetails;
import com.hacker.trading.entities.User;
import com.hacker.trading.repository.PaymentDetailsRepository;
import com.hacker.trading.service.PaymentDetailService;

@Service
public class PaymentDetailServiceImpl implements PaymentDetailService{

    @Autowired
    private PaymentDetailsRepository paymentDetailsRepository;

    @Override
    public PaymentDetails addPaymentDetails(String accountNumber, String accountHolderName, String ifsc,
            String bankName, User user) {
        PaymentDetails paymentDetails = new PaymentDetails();

        paymentDetails.setAccountNumber(accountNumber);
        paymentDetails.setAccountHolderName(accountHolderName);
        paymentDetails.setIfsc(ifsc);
        paymentDetails.setUser(user);

        return paymentDetailsRepository.save(paymentDetails);
    }

    @Override
    public PaymentDetails getUserPaymentDetails(User user) {
        return paymentDetailsRepository.findByUserId(user.getId());
    }

}
