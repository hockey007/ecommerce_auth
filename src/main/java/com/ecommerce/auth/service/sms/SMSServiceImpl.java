package com.ecommerce.auth.service.sms;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SMSServiceImpl implements ISMSService {

    @Value("${twilio.phone.number}")
    private String fromPhone;

    @Override
    public void sendMessage(String toPhone, Integer code) {
        Message message = Message.creator(
                new PhoneNumber("+91" + toPhone),
                new PhoneNumber(fromPhone),
                "Please use OTP " + code + " for mobile verification").create();

        System.out.println(message.getAccountSid());
    }
}
