package com.quickmart.service;

import com.quickmart.payload.CartDTO;

public interface CartService {
    CartDTO addProductToCart(Long productId, Integer quantity);

}
