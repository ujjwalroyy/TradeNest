package com.hacker.trading.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hacker.trading.entities.User;
import com.hacker.trading.entities.Wallet;
import com.hacker.trading.entities.WalletTransaction;
import com.hacker.trading.entities.Withdrawal;
import com.hacker.trading.service.UserService;
import com.hacker.trading.service.WalletService;
import com.hacker.trading.service.WithdrawalService;

import lombok.With;

@RestController
@RequestMapping("/api/withdrawal")
public class WithdrawalController {

    @Autowired
    private WithdrawalService withdrawalService;

    @Autowired
    private WalletService walletService;

    @Autowired
    private UserService userService;

    // @Autowired
    // private WalletTransactionService walletTransactionService;

    @PostMapping("/api/withdrawal/{amount}")
    public ResponseEntity<?> withdrawalRequest(@PathVariable Long amount, @RequestHeader("Authorization")String jwt) throws Exception{
        User user = userService.findUserProfileByJwt(jwt);
        Wallet userWallet = walletService.getUserWallet(user);
        Withdrawal withdrawal = withdrawalService.requestWithdrawal(amount, user);
        walletService.addBalance(userWallet, -withdrawal.getAmount());

        // WalletTransaction walletTransaction = walletTransactionService.createTransaction

        return new ResponseEntity<>(withdrawal, HttpStatus.OK);
    }

    @PostMapping("/api/admin/withdrawal/{id}/proceed/{accept}")
    public ResponseEntity<?> processWithdrawal(@PathVariable Long id, @PathVariable boolean accept, @RequestHeader("Authentication")String jwt) throws Exception{
        User user = userService.findUserProfileByJwt(jwt);
        Withdrawal withdrawal = withdrawalService.processWithdrawal(id, accept);
        Wallet userWallet = walletService.getUserWallet(user);
        
        if(!accept){
            walletService.addBalance(userWallet, withdrawal.getAmount());
        }
        return new ResponseEntity<>(withdrawal, HttpStatus.OK);
    }

    @GetMapping("/api/withdrawal")
    public ResponseEntity<List<Withdrawal>> getWithdrawalHistory(@RequestHeader("Authorization")String jwt) throws Exception{
        User user = userService.findUserProfileByJwt(jwt);
        List<Withdrawal> withdrawal = withdrawalService.getUserWithdrawalHistory(user);
        return new ResponseEntity<>(withdrawal, HttpStatus.OK);
    }

    @GetMapping("/api/admin/withdrawal")
    public ResponseEntity<List<Withdrawal>> getAllWithdrawalRequest(@RequestHeader("Authorization")String jwt) throws Exception{
        User user = userService.findUserProfileByJwt(jwt);
        List<Withdrawal> withdrawal = withdrawalService.getAllWithdrawalRequest();
        return new ResponseEntity<>(withdrawal, HttpStatus.OK);
    }
}


// 6:43:06