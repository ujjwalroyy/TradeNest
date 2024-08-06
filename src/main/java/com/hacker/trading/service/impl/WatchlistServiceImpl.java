package com.hacker.trading.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hacker.trading.entities.Coin;
import com.hacker.trading.entities.User;
import com.hacker.trading.entities.Watchlist;
import com.hacker.trading.repository.WatchlistRepository;
import com.hacker.trading.service.WatchlistService;

@Service
public class WatchlistServiceImpl implements WatchlistService {

    @Autowired
    private WatchlistRepository watchlistRepository;

    @Override
    public Watchlist findWatchlist(Long userId) throws Exception {
        Watchlist watchlist = watchlistRepository.findByUserId(userId);
        if(watchlist == null){
            throw new Exception("watchlist not found");
        }
        return watchlist;
    }

    @Override
    public Watchlist createWatchlist(User user) {
        Watchlist watchlist = new Watchlist();
        watchlist.setUser(user);
        return watchlistRepository.save(watchlist);
    }

    @Override
    public Watchlist findById(Long id) throws Exception {
        Optional<Watchlist> watchlisOptional = watchlistRepository.findById(id);
        if(watchlisOptional.isEmpty()){
            throw new Exception("watchlist not found");
        }
        return watchlisOptional.get();
    }

    @Override
    public Coin addItemToWatchlist(Coin coin, User user) throws Exception {
       Watchlist watchlist = findWatchlist(user.getId());
       if(watchlist.getCoins().contains(coin)){
        watchlist.getCoins().remove(coin);
       }
       else{
        watchlist.getCoins().add(coin);
       }
       watchlistRepository.save(watchlist);
       return coin;
    }

}
