package com.hacker.trading.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hacker.trading.entities.Order;

import java.util.*;

public interface OrderRepository extends JpaRepository<Order, Long>{
    List<Order> findByUserId(Long userId);
}
