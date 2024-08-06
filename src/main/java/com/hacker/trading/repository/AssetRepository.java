package com.hacker.trading.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
import com.hacker.trading.entities.Asset;

public interface AssetRepository extends JpaRepository<Asset, Long>{

    List<Asset> findByUserId(Long userId);
    Asset findByUserIdAndCoinId(Long userId, String coinId);
}
