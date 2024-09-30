package com.ecommerce.auth.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String name;
    private String mobile;
    private String line1;
    private String line2;

    @Enumerated(EnumType.ORDINAL)
    private AddressType type;

    @Column(name = "custom_type")
    @JsonProperty("custom_type")
    private String customType;

    private String city;
    private String state;
    private String country;
    private Integer pincode;
    private Boolean isPrimary;

}
