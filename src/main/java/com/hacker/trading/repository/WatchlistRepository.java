package com.hacker.trading.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hacker.trading.entities.Watchlist;

public interface WatchlistRepository extends JpaRepository<Watchlist, Long>{

    Watchlist findByUserId(Long userId);
}
