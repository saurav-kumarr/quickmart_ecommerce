package com.quickmart.service;

import com.quickmart.model.User;
import com.quickmart.payload.AddressDTO;

public interface AddressService {
    AddressDTO createAddress(AddressDTO addressDTO, User user);
}
