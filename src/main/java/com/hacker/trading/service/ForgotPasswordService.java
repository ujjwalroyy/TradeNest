package com.hacker.trading.service;

import com.hacker.trading.domain.VerificationType;
import com.hacker.trading.entities.ForgotPasswordToken;
import com.hacker.trading.entities.User;

public interface ForgotPasswordService {

    ForgotPasswordToken createToken(User user, String id, String otp, VerificationType verificationType, String sendTo);
    
    ForgotPasswordToken findById(String id);

    ForgotPasswordToken findByUser(Long userId);

    void deleteToken(ForgotPasswordToken token);
}
