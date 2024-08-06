package com.hacker.trading.service;

import com.hacker.trading.entities.User;
import com.hacker.trading.entities.Withdrawal;
import java.util.*;

public interface WithdrawalService {

    Withdrawal requestWithdrawal(Long amount, User user);
    
    Withdrawal processWithdrawal(Long withdrawalId, boolean accept) throws Exception;

    List<Withdrawal> getUserWithdrawalHistory(User user);

    List<Withdrawal> getAllWithdrawalRequest();
}
