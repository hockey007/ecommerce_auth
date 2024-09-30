package com.ecommerce.auth.service.user;

import com.ecommerce.auth.exception.EmailAlreadyInUseException;
import com.ecommerce.auth.exception.InvalidCredentialsException;
import com.ecommerce.auth.exception.UserNotFoundException;
import com.ecommerce.auth.model.OTP;
import com.ecommerce.auth.model.User;
import com.ecommerce.auth.repository.UserRepository;
import com.ecommerce.auth.service.otp.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OTPService otpService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(User user) {
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());

        if(optionalUser.isPresent()) {
            throw new EmailAlreadyInUseException("Email already in use");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void requestVerification(UUID userId, String mobile) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()) return;

        User user = optionalUser.get();
        user.setMobile(mobile);

        OTP otp = otpService.generateOTP(user);
    }

    public boolean verifyOTP(String mobile, Integer otp) {
        Optional<User> optionalUser = userRepository.findByMobile(mobile);

        if(optionalUser.isEmpty()) {
            throw new IllegalStateException("User not found");
        }

        return otpService.verifyOTP(optionalUser.get(), otp);
    }

    public void emailLogin(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        User user = optionalUser.get();
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }
    }

}
