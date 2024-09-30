package com.ecommerce.auth.controller;

import com.ecommerce.auth.model.Address;
import com.ecommerce.auth.service.address.AddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressServiceImpl addressService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<String> addAddress(@PathVariable UUID userId, @RequestBody Address address) {
        addressService.add(userId, address);
        return ResponseEntity.ok("Address added successfully");
    }
}
