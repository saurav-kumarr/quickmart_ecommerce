package com.quickmart.service;

import com.quickmart.model.Product;
import com.quickmart.payload.ProductDTO;
import com.quickmart.payload.ProductResponse;

public interface ProductService {

    ProductDTO addProduct(Long categoryId, Product product);

    ProductResponse getAllProducts();

    ProductResponse searchByCategory(Long categoryId);

    ProductResponse searchProductByKeyword(String keyword);

}
