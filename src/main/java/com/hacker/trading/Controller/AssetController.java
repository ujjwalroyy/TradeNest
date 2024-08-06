package com.hacker.trading.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hacker.trading.entities.Asset;
import com.hacker.trading.entities.User;
import com.hacker.trading.service.AssetService;
import com.hacker.trading.service.UserService;
import java.util.*;

@RestController
@RequestMapping("/api/asset")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @Autowired
    private UserService userService;

    @GetMapping("/{assetId}")
    public ResponseEntity<Asset> getAssetById(@PathVariable Long assetId) throws Exception{
        Asset asset = assetService.getAssetById(assetId);
        return ResponseEntity.ok().body(asset);
    }

    @GetMapping("/coin/{coinId}/user")
    public ResponseEntity<Asset> getAssetByUserIdAndCoinId(
        @PathVariable String coinId,
        @RequestHeader("Authorization") String jwt
    ) throws Exception{

        User user = userService.findUserProfileByJwt(jwt);
        Asset asset = assetService.findAssetByUserIdAndCoinId(user.getId(), coinId);
        return ResponseEntity.ok().body(asset);
    }

    @GetMapping()
    public ResponseEntity<List<Asset>> getAssetForUser(@RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserProfileByJwt(jwt);
        List<Asset> assets = assetService.getUserAssets(user.getId());
        return ResponseEntity.ok().body(assets);
    }
}
