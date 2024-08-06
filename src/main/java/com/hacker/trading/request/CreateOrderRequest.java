package com.hacker.trading.request;

import com.hacker.trading.domain.OrderType;

import lombok.Data;

@Data
public class CreateOrderRequest {

    private String coinId;

    private double quantity;

    private OrderType orderType;
}
