package com.quickmart.service;

import com.quickmart.model.User;
import com.quickmart.payload.AddressDTO;

import java.util.List;

public interface AddressService {
    AddressDTO createAddress(AddressDTO addressDTO, User user);

    List<AddressDTO> getAddress();
}
