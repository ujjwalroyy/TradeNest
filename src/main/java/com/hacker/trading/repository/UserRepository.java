package com.hacker.trading.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hacker.trading.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
    public User findByEmail(String email);

}
