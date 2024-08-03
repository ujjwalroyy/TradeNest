package com.hacker.trading.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hacker.trading.entities.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long>{

    Wallet findByUserId(Long userId);
}
