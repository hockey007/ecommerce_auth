package com.ecommerce.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OTPValidationRequestDTO {
    private String mobile;
    private Integer otp;
}
