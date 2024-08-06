package com.hacker.trading.Controller;

import org.apache.catalina.connector.Response;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hacker.trading.domain.OrderType;
import com.hacker.trading.entities.Coin;
import com.hacker.trading.entities.Order;
import com.hacker.trading.entities.User;
import com.hacker.trading.request.CreateOrderRequest;
import com.hacker.trading.service.CoinService;
import com.hacker.trading.service.OrderService;
import com.hacker.trading.service.UserService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private CoinService coinService;

    // @Autowired
    // private WalletTransactionService WalletTransactionService;

    @PostMapping("/pay")
    public ResponseEntity<Order> payOrderPayment(@RequestHeader("Authorization") String jwt, @RequestBody CreateOrderRequest req) throws Exception{
        User user = userService.findUserProfileByJwt(jwt);
        Coin coin = coinService.findById(req.getCoinId());
        Order order = orderService.processOrder(coin, req.getQuantity(), req.getOrderType(), user);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@RequestHeader("Authorization") String jwtToken, @PathVariable Long orderId) throws Exception{
        User user = userService.findUserProfileByJwt(jwtToken);
        Order order = orderService.getOrderById(orderId);
        if(order.getUser().getId().equals(user.getId())){
            return ResponseEntity.ok(order);
        }
        else{
            throw new Exception("you don't have access");
        }
    }
    

    public ResponseEntity<List<Order>> getAllOrdersForUser(
        @RequestHeader("Authorization") String jwt,
        @RequestParam(required = false) OrderType order_type,
        @RequestParam(required = false) String asset_symbol) throws Exception{
            
            Long userId = userService.findUserProfileByJwt(jwt).getId();
            List<Order> userOrders = orderService.getAllOrdersOfUser(userId, order_type, asset_symbol);
            return ResponseEntity.ok(userOrders);
    }
}
