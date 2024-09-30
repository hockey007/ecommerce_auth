package com.ecommerce.auth.service.otp;

import com.ecommerce.auth.model.OTP;
import com.ecommerce.auth.model.User;

public interface IOTPService {
    OTP generateOTP(User user);
    Boolean verifyOTP(User user, Integer code);
}
