package com.ecommerce.service;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.payload.ProductRequest;
import com.ecommerce.payload.ProductResponse;

public interface ProductService {
	ProductDTO addProduct(Long categoryId, ProductRequest productRequest);

	ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
	
	ProductResponse searchByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    ProductResponse searchProductByKeyword(String keyword, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    ProductDTO updateProduct(Long productId, ProductRequest productRequest);

    ProductDTO deleteProduct(Long productId);
}
