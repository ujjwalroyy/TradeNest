package com.hacker.trading.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hacker.trading.domain.OrderStatus;
import com.hacker.trading.domain.OrderType;
import com.hacker.trading.entities.Coin;
import com.hacker.trading.entities.Order;
import com.hacker.trading.entities.OrderItem;
import com.hacker.trading.entities.User;
import com.hacker.trading.service.OrderService;
import com.hacker.trading.service.WalletService;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WalletService walletService;

    @Override
    public Order createOrder(User user, OrderItem orderItem, OrderType orderType) {
        double price = orderItem.getCoin().getCurrentPrice()*orderItem.getQuantity();
        Order order = new Order();
        order.setUser(user);
        order.setOrderItem(orderItem);
        order.setOrderType(orderType);
        order.setPrice(BigDecimal.valueOf(price));
        order.setTimeStamp(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long orderId) {
       
    }

    @Override
    public List<Order> getAllOrdersOfUser(Long userId, OrderType orderType, String assetSymbol) {
       
    }

    @Override
    public Order processOrder(Coin coin, double quantity, OrderType orderType, User user) {
       
    }

}


5:37:44