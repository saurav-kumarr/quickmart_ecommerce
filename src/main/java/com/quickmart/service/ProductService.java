package com.quickmart.service;

import com.quickmart.model.Product;
import com.quickmart.payload.ProductDTO;

public interface ProductService {

    ProductDTO addProduct(Long categoryId, Product product);
}
