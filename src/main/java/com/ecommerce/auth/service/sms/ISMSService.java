package com.ecommerce.auth.service.sms;

public interface ISMSService {
    void sendMessage(String mobile, Integer code);
}
