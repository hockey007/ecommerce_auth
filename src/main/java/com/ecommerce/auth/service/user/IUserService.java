package com.ecommerce.auth.service.user;

import com.ecommerce.auth.model.OTP;
import com.ecommerce.auth.model.User;

import java.util.UUID;

public interface IUserService {
    void register(User user);
    void requestVerification(UUID userId, String mobile);
    boolean verifyOTP(String mobile, Integer otp);
}
