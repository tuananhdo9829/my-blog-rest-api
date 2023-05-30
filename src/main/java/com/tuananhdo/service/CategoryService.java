package com.tuananhdo.service;

import com.tuananhdo.payload.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO addCategory(CategoryDTO categoryDTO);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);

    void deleteCategoryById(long id);

    CategoryDTO getCategoryById(long categoryId);

    List<CategoryDTO> getAllCategories();
}
