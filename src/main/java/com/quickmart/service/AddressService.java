package com.quickmart.service;

import com.quickmart.model.User;
import com.quickmart.payload.AddressDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface AddressService {
    AddressDTO createAddress(AddressDTO addressDTO, User user);

    List<AddressDTO> getAddress();

    AddressDTO getAddressById(Long addressId);

    List<AddressDTO> getUserAddress(User user);

    AddressDTO updateAddress(Long addressId,  AddressDTO addressDTO);

    String deleteAddress(Long addressId);
}
