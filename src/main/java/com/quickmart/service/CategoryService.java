package com.quickmart.service;

import com.quickmart.model.Category;
import com.quickmart.payload.CategoryDTO;
import com.quickmart.payload.CategoryResponse;


public interface CategoryService {

    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize);
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO deleteCategory(Long categoryId);
    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);
}
