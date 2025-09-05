package com.quickmart.repositories;

import com.quickmart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long>{


}
