package com.ecommerce.service;

import com.ecommerce.dto.CategoryDTO;
import com.ecommerce.payload.CategoryRequest;
import com.ecommerce.payload.CategoryResponse;

public interface CategoryService {
	CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
	CategoryDTO createCategory(CategoryRequest categoryRequest);
	CategoryDTO deleteCategory(Long categoryId);
	CategoryDTO updateCategory(CategoryRequest categoryRequest, Long categoryId);
}
