package com.hacker.trading.service;

import com.hacker.trading.entities.PaymentDetails;
import com.hacker.trading.entities.User;

public interface PaymentDetailService {

    public PaymentDetails addPaymentDetails(String accountNumber, String accountHolderName, String ifsc,
            String bankName, User user);

    public PaymentDetails getUserPaymentDetails(User user);
}
