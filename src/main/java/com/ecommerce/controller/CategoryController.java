package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.config.AppConstants;
import com.ecommerce.payload.CategoryRequest;
import com.ecommerce.payload.CategoryResponse;
import com.ecommerce.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/public/categories")
	//@RequestMapping(value = "/public/categories" , method = RequestMethod.GET)
	public ResponseEntity<CategoryResponse> getAllCategories(
			@RequestParam(name = "pageNumber" , defaultValue = AppConstants.PAGE_NUMBER , required = false) Integer pageNumber,
			@RequestParam(name = "pageSize" , defaultValue = AppConstants.PAGE_SIZE , required = false) Integer pageSize,
			@RequestParam(name = "sortBy" , defaultValue = AppConstants.SORT_CATEGORIES_BY , required = false) String sortBy,
			@RequestParam(name = "sortOrder" , defaultValue = AppConstants.SORT_DIR , required = false) String sortOrder
			){
		return new ResponseEntity<>(categoryService.getAllCategories(pageNumber , pageSize , sortBy , sortOrder), HttpStatus.OK);
	}
	
	@PostMapping("/public/categories")
	public ResponseEntity<String> createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
		categoryService.createCategory(categoryRequest);
		return new ResponseEntity<>("Category created successfully" , HttpStatus.OK);
	}
	
	@DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
		categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>("Category Deleted sucessfully", HttpStatus.OK);
    }
	
	@PutMapping("/admin/categories/{categoryId}")
	public ResponseEntity<String> updateCategory(@Valid @RequestBody CategoryRequest categoryRequest , @PathVariable Long categoryId){
		categoryService.updateCategory(categoryRequest , categoryId);
		return new ResponseEntity<> ("Category updated successfully" , HttpStatus.OK);
	}
}
