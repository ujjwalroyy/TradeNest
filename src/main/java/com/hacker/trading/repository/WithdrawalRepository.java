package com.hacker.trading.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
import com.hacker.trading.entities.Withdrawal;

public interface WithdrawalRepository extends JpaRepository<Withdrawal, Long>{

    List<Withdrawal> findByUserId(Long userId);
}
