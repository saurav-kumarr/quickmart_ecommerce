package com.quickmart.service;

import com.quickmart.payload.CartDTO;

import java.util.List;

public interface CartService {


    CartDTO addProductToCart(Long productId, Integer quantity);

    List<CartDTO> getAllCarts();

}
