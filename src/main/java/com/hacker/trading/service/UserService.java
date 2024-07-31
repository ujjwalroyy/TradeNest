package com.hacker.trading.service;

import com.hacker.trading.domain.VerificationType;
import com.hacker.trading.entities.User;

public interface UserService {

    public User findUserProfileByJwt(String jwt) throws Exception;
    public User findUserByEmail(String email) throws Exception;
    public User findUserById(Long userId) throws Exception;
    public User enableTwoFactorAuthentication(VerificationType verificationType, String sendTo, User user);
    User updatePassword(User user, String newPassword);
}
