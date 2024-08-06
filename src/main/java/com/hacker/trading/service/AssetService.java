package com.hacker.trading.service;

import com.hacker.trading.entities.Asset;
import com.hacker.trading.entities.Coin;
import com.hacker.trading.entities.User;
import java.util.*;

public interface AssetService{

    Asset createAsset(User user, Coin coin, double quantity);
    Asset getAssetById(Long assetId) throws Exception;
    Asset getAssetByUserIdAndId(Long userId, Long assetId);
    List<Asset> getUserAssets(Long userId);
    Asset updateAsset(Long assetId, double quantity) throws Exception;
    Asset findAssetByUserIdAndCoinId(Long userId, String coinId);
    void deleteAsset(Long assetId);

}
