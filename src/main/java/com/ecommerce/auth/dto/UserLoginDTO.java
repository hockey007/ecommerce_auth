package com.ecommerce.auth.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDTO {
    @Column(name = "username")
    private String email;
    private String password;
}
