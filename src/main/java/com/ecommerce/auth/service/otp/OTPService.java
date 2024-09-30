package com.ecommerce.auth.service.otp;

import com.ecommerce.auth.model.OTP;
import com.ecommerce.auth.model.User;
import com.ecommerce.auth.repository.OTPRepository;
import com.ecommerce.auth.repository.UserRepository;
import com.ecommerce.auth.service.sms.SMSServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
public class OTPService implements IOTPService {

    @Autowired
    private OTPRepository otpRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SMSServiceImpl smsService;

    @Override
    public OTP generateOTP(User user) {
        invalidateOTP(user);

        OTP otp = new OTP();
        otp.setUser(user);
        otp.setCode(generateRandomOTP());
        otp.setExpiry(LocalDateTime.now().plusMinutes(2));

        OTP savedOtp = otpRepository.save(otp);
        user.setOtp(savedOtp);
        userRepository.save(user);

        smsService.sendMessage(user.getMobile(), savedOtp.getCode());
        return savedOtp;
    }

    @Override
    public Boolean verifyOTP(User user, Integer code) {
        if(user.getOtp() == null || user.getOtp().getExpiry().isBefore(LocalDateTime.now())) return false;
        return Objects.equals(user.getOtp().getCode(), code);
    }

    private void invalidateOTP(User user) {
        OTP otp = user.getOtp();
        if(otp != null) {
            otpRepository.delete(otp);
            user.setOtp(null);
            userRepository.save(user);
        }
    }

    private Integer generateRandomOTP() {
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }
}
