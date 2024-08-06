package com.hacker.trading.service;

import java.util.List;

import com.hacker.trading.domain.OrderType;
import com.hacker.trading.entities.Coin;
import com.hacker.trading.entities.Order;
import com.hacker.trading.entities.OrderItem;
import com.hacker.trading.entities.User;

public interface OrderService {
    
    Order createOrder(User user, OrderItem orderItem, OrderType orderType);

    Order getOrderById(Long orderId) throws Exception;
    
    List<Order> getAllOrdersOfUser(Long userId, OrderType orderType, String assetSymbol);

    Order processOrder(Coin coin, double quantity, OrderType orderType, User user) throws Exception;

    

}
