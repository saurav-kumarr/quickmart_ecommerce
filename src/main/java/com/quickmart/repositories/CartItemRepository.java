package com.quickmart.repositories;

import com.quickmart.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository <CartItem,Long> {
}
