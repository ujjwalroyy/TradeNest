package com.hacker.trading.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hacker.trading.domain.VerificationType;
import com.hacker.trading.entities.User;
import com.hacker.trading.entities.VerificationCode;
import com.hacker.trading.repository.VerificationCodeRepository;
import com.hacker.trading.service.VerificationCodeService;
import com.hacker.trading.utils.OtpUtils;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService{

    @Autowired
    private VerificationCodeRepository verificationCodeRepository;


    @Override
    public VerificationCode sendVerificationCode(User user, VerificationType verificationType) {
       VerificationCode verificationCode1 = new VerificationCode();
       verificationCode1.setOtp(OtpUtils.generateOTP());
       verificationCode1.setVerificationType(verificationType);
       verificationCode1.setUser(user);
       return verificationCodeRepository.save(verificationCode1);
    }

    @Override
    public VerificationCode getVerificationCodeById(Long id) throws Exception {
     Optional<VerificationCode> verificationCode = verificationCodeRepository.findById(id);
     if(verificationCode.isPresent()){
        return verificationCode.get();
     }
     throw new Exception("Verification code not found");
    }

    @Override
    public VerificationCode getVerificationCodeByUser(Long userId) {
     return verificationCodeRepository.findByUserId(userId);
    }

    @Override
    public void deleteVerificationCodeById(VerificationCode verificationCode) {
        verificationCodeRepository.delete(verificationCode);
    }

}
