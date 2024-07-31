package com.hacker.trading.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hacker.trading.entities.VerificationCode;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {

    public VerificationCode findByUserId(Long userId);
}
