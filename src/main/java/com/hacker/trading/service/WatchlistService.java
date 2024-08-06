package com.hacker.trading.service;

import com.hacker.trading.entities.Coin;
import com.hacker.trading.entities.User;
import com.hacker.trading.entities.Watchlist;

public interface WatchlistService {

    Watchlist findWatchlist(Long userId) throws Exception;
    Watchlist createWatchlist(User user);
    Watchlist findById(Long id) throws Exception;

    Coin addItemToWatchlist(Coin coin, User user) throws Exception;

}
