package com.hacker.trading.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hacker.trading.entities.PaymentOrder;

public interface PaymentOrderRepository extends JpaRepository<PaymentOrder, Long>{

}
