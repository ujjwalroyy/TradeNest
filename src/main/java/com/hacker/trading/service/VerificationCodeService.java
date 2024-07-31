package com.hacker.trading.service;

import com.hacker.trading.domain.VerificationType;
import com.hacker.trading.entities.User;
import com.hacker.trading.entities.VerificationCode;

public interface VerificationCodeService {

    VerificationCode sendVerificationCode(User user, VerificationType verificationType);

    VerificationCode getVerificationCodeById(Long id) throws Exception;

    VerificationCode getVerificationCodeByUser(Long userId);

    // Boolean VerifyOtp = String otp

    void deleteVerificationCodeById(VerificationCode verificationCode);
}
