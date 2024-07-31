package com.hacker.trading.request;

import com.hacker.trading.domain.VerificationType;

import lombok.Data;

@Data
public class ForgotPasswordTokenRequest {

    private String sendTo;
    private VerificationType verificationType;
}
