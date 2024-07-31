package com.hacker.trading.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.hacker.trading.entities.TwoFactorOTP;
import com.hacker.trading.entities.User;
import com.hacker.trading.repository.TwoFactorOtpRepository;
import com.hacker.trading.service.TwoFactorOtpService;

@Service
public class TwoFactorOtpServiceImpl implements TwoFactorOtpService {

    @Autowired
    private TwoFactorOtpRepository twoFactorOtpRepository;

    @Override
    public TwoFactorOTP createTwoFactorOtp(User user, String otp, String jwt) {
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        TwoFactorOTP twoFactorOTP = new TwoFactorOTP();
        twoFactorOTP.setOtp(otp);
        twoFactorOTP.setJwt(jwt);
        twoFactorOTP.setId(id);
        twoFactorOTP.setUser(user);
        return twoFactorOtpRepository.save(twoFactorOTP);

    }

    @Override
    public TwoFactorOTP findByUser(Long userId) {
        return twoFactorOtpRepository.findByUserId(userId);
    }

    @Override
    public TwoFactorOTP findById(String id) {
       Optional<TwoFactorOTP> otp = twoFactorOtpRepository.findById(id);
       return otp.orElse(null);
    }

    @Override
    public boolean verifyTwoFactorOtp(TwoFactorOTP twoFactorOtp, String otp) {
       return twoFactorOtp.getOtp().equals(otp);
    }

    @Override
    public void deleteTwoFactorOtp(TwoFactorOTP twoFactorOtp) {
        twoFactorOtpRepository.delete(twoFactorOtp);
    }


}
