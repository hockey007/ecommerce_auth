package com.ecommerce.auth.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class VerificationRequestDTO {
    private UUID userId;
    private String mobile;
}
