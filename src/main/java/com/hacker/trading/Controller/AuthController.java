package com.hacker.trading.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hacker.trading.config.JwtProvider;
import com.hacker.trading.entities.TwoFactorAuth;
import com.hacker.trading.entities.TwoFactorOTP;
import com.hacker.trading.entities.User;
import com.hacker.trading.repository.UserRepository;
import com.hacker.trading.response.AuthResponse;
import com.hacker.trading.service.EmailService;
import com.hacker.trading.service.TwoFactorOtpService;
import com.hacker.trading.service.impl.CustomeUserDetailsService;
import com.hacker.trading.utils.OtpUtils;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomeUserDetailsService customeUserDetailsService;

    @Autowired
    private TwoFactorOtpService twoFactorOtpService;
    
    @Autowired
    private EmailService emailService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> register(@RequestBody User user) throws Exception{
        

        User isEmailExist = userRepository.findByEmail(user.getEmail());

        if(isEmailExist != null){
            throw new Exception("email is already exist...");
        }

        User newUser = new User();
        newUser.setFullName(user.getFullName());
        newUser.setEmail((user.getEmail()));
        newUser.setPassword(user.getPassword());
        
        User savedUser = userRepository.save(newUser);
        Authentication auth = new UsernamePasswordAuthenticationToken(
            user.getEmail(),
            user.getPassword()
        );

        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt = JwtProvider.generateToken(auth);

        AuthResponse res = new AuthResponse();
        res.setJwt(jwt);
        res.setStatus(true);
        res.setMessage("register success");

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> login(@RequestBody User user) throws Exception{
        String userName = user.getEmail();
        String password = user.getPassword();

        Authentication auth = authenticate(userName, password);

        SecurityContextHolder.getContext().setAuthentication(auth);

        User authUser = userRepository.findByEmail(userName);

        String jwt = JwtProvider.generateToken(auth);

        if(user.getTwoFactorAuth().isEnabled()){
            AuthResponse res = new AuthResponse();
            res.setMessage("Two factor auth is enabled");
            res.setTwoFactorAuthEnabled(true);
            String otp =OtpUtils.generateOTP();

            TwoFactorOTP oldTwoFactorOTP = twoFactorOtpService.findByUser(authUser.getId()); 

            if(oldTwoFactorOTP != null){
                twoFactorOtpService.deleteTwoFactorOtp(oldTwoFactorOTP);
            }
            TwoFactorOTP newTwoFactorOTP = twoFactorOtpService.createTwoFactorOtp(authUser, otp, jwt);

            emailService.sendVerificationOtpEmail(userName, otp);
            

            res.setSession(newTwoFactorOTP.getId());
            return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
        }

        AuthResponse res = new AuthResponse();
        res.setJwt(jwt);
        res.setStatus(true);
        res.setMessage("Login success");

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    private Authentication authenticate(String userName, String password) {
        UserDetails userDetails = customeUserDetailsService.loadUserByUsername(userName);

        if(userDetails == null){
            throw new BadCredentialsException("Invalid Username...");
        }

        if(!password.equals(userDetails.getPassword())){
            throw new BadCredentialsException("Invalid Password...");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    @PostMapping("/two-factor/otp/{otp}")
    public ResponseEntity<AuthResponse> verifySigingOtp(@PathVariable String otp, @RequestParam String id) throws Exception{

        TwoFactorOTP twoFactorOTP = twoFactorOtpService.findById(id);
        if(twoFactorOtpService.verifyTwoFactorOtp(twoFactorOTP, otp)){
            AuthResponse res = new AuthResponse();
            res.setMessage("Two factor authentication verified");
            res.setTwoFactorAuthEnabled(true);
            res.setJwt(twoFactorOTP.getJwt());
            return new ResponseEntity<>(res, HttpStatus.OK);
        }

        throw new Exception("Invalid OTP");
    }
}


// 1: 35