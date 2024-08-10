package com.hacker.trading.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
import com.hacker.trading.entities.Order;
import com.hacker.trading.entities.PaymentOrder;
import com.hacker.trading.entities.User;
import com.hacker.trading.entities.Wallet;
import com.hacker.trading.entities.WalletTransaction;
import com.hacker.trading.service.OrderService;
import com.hacker.trading.service.PaymentService;
import com.hacker.trading.service.UserService;
import com.hacker.trading.service.WalletService;

@RestController
public class WalletController {

    @Autowired
    private WalletService walletService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/api/wallet")
    public ResponseEntity<Wallet> getUserNameWallet(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Wallet wallet = walletService.getUserWallet(user);
        return new ResponseEntity<>(wallet, HttpStatus.ACCEPTED);
    }

    @PutMapping("/api/wallet/{walletId}/transfer")
    public ResponseEntity<Wallet> walletToWalletTransfer(@RequestHeader("Authorization") String jwt,
            @PathVariable Long walletId, @RequestBody WalletTransaction req) throws Exception {

        User senderUser = userService.findUserProfileByJwt(jwt);
        Wallet receiveWallet = walletService.findWalletById(walletId);
        Wallet wallet = walletService.walletToWalletTransfer(senderUser, receiveWallet, req.getAmount());

        return new ResponseEntity<>(wallet, HttpStatus.ACCEPTED);
    }

    @PutMapping("/api/wallet/order/{orderId}/pay")
    public ResponseEntity<Wallet> payOrderPayment(@RequestHeader("Authorization") String jwt,
            @PathVariable Long orderId) throws Exception {

        User user = userService.findUserProfileByJwt(jwt);
        Order order = orderService.getOrderById(orderId);
        Wallet wallet = walletService.payOrderPayment(order, user);

        return new ResponseEntity<>(wallet, HttpStatus.ACCEPTED);
    }

    @PutMapping("/api/wallet/deposite")
    public ResponseEntity<Wallet> addMoneyToWallet(@RequestHeader("Authorization") String jwt,
            @RequestParam(name = "order_id") Long orderId, @RequestParam(name = "payment_id") String paymentId)
            throws Exception {

        User user = userService.findUserProfileByJwt(jwt);
        Wallet wallet = walletService.getUserWallet(user);
        PaymentOrder order = paymentService.getPaymentOrderById(orderId);
        Boolean status = paymentService.proccedPaymentOrder(order, paymentId);
        if(wallet.getBalance() == null){
            wallet.setBalance(BigDecimal.valueOf(0));
        }
        if(status){
            wallet = walletService.addBalance(wallet, order.getAmount());
        }
        return new ResponseEntity<>(wallet, HttpStatus.ACCEPTED);
    }

}
