package com.hacker.trading.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hacker.trading.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
