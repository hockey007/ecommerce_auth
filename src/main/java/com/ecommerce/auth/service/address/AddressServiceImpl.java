package com.ecommerce.auth.service.address;

import com.ecommerce.auth.exception.UserNotFoundException;
import com.ecommerce.auth.model.Address;
import com.ecommerce.auth.model.User;
import com.ecommerce.auth.repository.AddressRepository;
import com.ecommerce.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public void add(UUID userId, Address address) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        User user = optionalUser.get();
        address.setUser(user);

        addressRepository.save(address);
    }

    @Override
    public void update(UUID addressId, Address address) {

    }

    @Override
    public void delete(UUID addressId) {

    }

    @Override
    public List<Address> getAll(UUID userId) {
        return null;
    }
}
