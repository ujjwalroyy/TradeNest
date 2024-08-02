package com.hacker.trading.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hacker.trading.entities.Coin;

public interface CoinRepository extends JpaRepository<Coin, String>{

    
} 