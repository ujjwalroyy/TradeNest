package com.hacker.trading.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hacker.trading.entities.PaymentDetails;

public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, Long>{

    PaymentDetails findByUserId(Long userId);
}
