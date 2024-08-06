package com.hacker.trading.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.hacker.trading.domain.WithdrawalStatus;
import com.hacker.trading.entities.User;
import com.hacker.trading.entities.Withdrawal;
import com.hacker.trading.repository.WithdrawalRepository;
import com.hacker.trading.service.WithdrawalService;
import java.time.LocalDateTime;

@Service
public class WithdrawalServiceImpl implements WithdrawalService{

    @Autowired
    private WithdrawalRepository withdrawalRepository;

    @Override
    public Withdrawal requestWithdrawal(Long amount, User user) {
       Withdrawal withdrawal = new Withdrawal();
       withdrawal.setAmount(amount);
       withdrawal.setUser(user);
       withdrawal.setStatus(WithdrawalStatus.PENDING);
       return withdrawalRepository.save(withdrawal);
    }

    @Override
    public Withdrawal processWithdrawal(Long withdrawalId, boolean accept) throws Exception {
        Optional<Withdrawal> withdrawal = withdrawalRepository.findById(withdrawalId);
        if(withdrawal.isEmpty()){
            throw new Exception("withdrawal not found");
        }
        Withdrawal withdrawal1 = withdrawal.get();
        withdrawal1.setDate(LocalDateTime.now());
        if(accept){
            withdrawal1.setStatus(WithdrawalStatus.SUCCESS);
        }
        else{
            withdrawal1.setStatus(WithdrawalStatus.PENDING);
        }
        return withdrawalRepository.save(withdrawal1);
    }

    @Override
    public List<Withdrawal> getUserWithdrawalHistory(User user) {
        return withdrawalRepository.findByUserId(user.getId());
    }

    @Override
    public List<Withdrawal> getAllWithdrawalRequest() {
        return withdrawalRepository.findAll();
    }


}
