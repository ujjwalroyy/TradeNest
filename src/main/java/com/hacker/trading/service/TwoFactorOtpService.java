package com.hacker.trading.service;

import com.hacker.trading.entities.TwoFactorOTP;
import com.hacker.trading.entities.User;

public interface TwoFactorOtpService {
    
    TwoFactorOTP createTwoFactorOtp(User user, String otp, String jwt);

    TwoFactorOTP findByUser(Long userId);
    
    TwoFactorOTP findById(String id);

    boolean verifyTwoFactorOtp(TwoFactorOTP twoFactorOtp, String otp);

    void deleteTwoFactorOtp(TwoFactorOTP twoFactorOtp);

}
