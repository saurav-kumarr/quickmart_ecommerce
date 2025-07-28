package com.quickmart.service;

import com.quickmart.model.Category;
import com.quickmart.payload.CategoryDTO;
import com.quickmart.payload.CategoryResponse;


public interface CategoryService {

    CategoryResponse getAllCategories();
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    String deleteCategory(Long categoryId);
    Category updateCategory(Category category, Long categoryId);
}
