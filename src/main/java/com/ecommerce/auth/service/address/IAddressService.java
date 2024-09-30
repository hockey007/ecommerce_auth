package com.ecommerce.auth.service.address;

import com.ecommerce.auth.model.Address;

import java.util.List;
import java.util.UUID;

public interface IAddressService {
    void add(UUID userId, Address address);
    void update(UUID addressId, Address address);
    void delete(UUID addressId);
    List<Address> getAll(UUID userId);
}
