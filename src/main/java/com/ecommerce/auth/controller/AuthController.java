package com.ecommerce.auth.controller;

import com.ecommerce.auth.dto.OTPValidationRequestDTO;
import com.ecommerce.auth.dto.UserLoginDTO;
import com.ecommerce.auth.dto.UserRegistrationDTO;
import com.ecommerce.auth.dto.VerificationRequestDTO;
import com.ecommerce.auth.model.User;
import com.ecommerce.auth.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegistrationDTO registrationDTO) {
        userServiceImpl.register(fromRegistrationDto(registrationDTO));
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/request-verify")
    public void requestVerification(@RequestBody VerificationRequestDTO verificationRequestDTO) {
        userServiceImpl.requestVerification(verificationRequestDTO.getUserId(), verificationRequestDTO.getMobile());
    }

    @PostMapping("/verify")
    public boolean verify(@RequestBody OTPValidationRequestDTO validationRequestDTO) {
        return userServiceImpl.verifyOTP(validationRequestDTO.getMobile(), validationRequestDTO.getOtp());
    }

    @PostMapping("/login/email")
    public ResponseEntity<String> login(@RequestBody UserLoginDTO loginDTO) {
        userServiceImpl.emailLogin(loginDTO.getEmail(), loginDTO.getPassword());
        return ResponseEntity.ok("Login successful");
    }

    private User fromRegistrationDto(UserRegistrationDTO registrationDTO) {
        User user = new User();
        user.setFirstName(registrationDTO.getFirstName());
        user.setLastName(registrationDTO.getLastName());
        user.setEmail(registrationDTO.getEmail());
        user.setPassword(registrationDTO.getPassword());

        return user;
    }

}
