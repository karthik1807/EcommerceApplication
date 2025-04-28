package com.ecommerce.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.CategoryDTO;
import com.ecommerce.exceptions.APIexception;
import com.ecommerce.model.Category;
import com.ecommerce.payload.CategoryRequest;
import com.ecommerce.payload.CategoryResponse;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
    private ModelMapper modelMapper;
	
	
	@Override
	public CategoryResponse getAllCategories(Integer pageNumber , Integer pageSize , String sortBy , String sortOrder) {
		Sort sortByandSortOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable pageDetails = PageRequest.of(pageNumber , pageSize , sortByandSortOrder);
		Page<Category> categoryPage = categoryRepository.findAll(pageDetails);
		
		
		List<Category> categories = categoryPage.getContent();
		if(categories.isEmpty()) {
			throw new APIexception("No category created till now");
		}
		
		List<CategoryDTO> categoryDTOS = new ArrayList<>();
		for (Category category : categories) {
		    CategoryDTO dto = modelMapper.map(category, CategoryDTO.class);
		    categoryDTOS.add(dto);
		}
		
		CategoryResponse categoryResponse = new CategoryResponse();
		categoryResponse.setCategoryContent(categoryDTOS);
		categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setTotalElements(categoryPage.getTotalElements());
        categoryResponse.setTotalpages(categoryPage.getTotalPages());
        categoryResponse.setLastPage(categoryPage.isLast());
		return categoryResponse;
	}

	@Override
	public CategoryDTO createCategory(CategoryRequest categoryRequest) {
		
		Category existingCategory  = categoryRepository.findByCategoryName(categoryRequest.getCategoryName());
		if(existingCategory  != null) {
			throw new APIexception("Category with name " + categoryRequest.getCategoryName() + " already exists !!");
		}
		
		Category category = modelMapper.map(categoryRequest, Category.class);
		
		Category savedCategory = categoryRepository.save(category);
		return modelMapper.map(savedCategory, CategoryDTO.class);

	}

	@Override
    public CategoryDTO deleteCategory(Long categoryId) {
		Category delCategory = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new APIexception("Category not found with id "+ categoryId));
		categoryRepository.delete(delCategory);
		
		return modelMapper.map(delCategory, CategoryDTO.class);
    }

	@Override
    public CategoryDTO updateCategory(CategoryRequest categoryRequest, Long categoryId) {
		Category existingCategory = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new APIexception("Resource not found"));
		Category category =  modelMapper.map(categoryRequest, Category.class);
		category.setCategoryId(existingCategory.getCategoryId());
		
		existingCategory = categoryRepository.save(category);
		
		CategoryDTO categoryDTO =  modelMapper.map(existingCategory, CategoryDTO.class);
		return categoryDTO;
    }

}
